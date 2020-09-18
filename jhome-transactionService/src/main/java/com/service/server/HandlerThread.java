package com.service.server;

import com.alibaba.fastjson.JSONObject;
import com.bracket.common.Bus.IBus;
import com.bracket.common.Bus.PushInfo;
import com.bracket.common.Queue.Bus;
import com.bracket.common.Queue.QueueHandler;
import com.service.config.ConfigInfo;
import com.service.model.MessageType;
import com.service.model.Transactionlog;
import com.service.service.TransactionlogService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::O
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @description: 处理线程
 * @author: Daxv
 * @create: 2020-09-16 18:41
 **/
@Getter
@Setter
@Service
@NoArgsConstructor
public class HandlerThread implements Runnable {
    private Logger logger= LoggerFactory.getLogger(HandlerThread.class);
    @Autowired
    private TransactionlogService transactionlogService;
    @Autowired
    private Bus bus;
    @Autowired
    private ConfigInfo configInfo;

    private String pushInfo;

    public HandlerThread(String pushInfo) {
        super();
        this.pushInfo = pushInfo;
    }


    @Override
    public void run() {
        JSONObject jsonObject= (JSONObject) JSONObject.parse(this.pushInfo);
        int pushType=jsonObject.getInteger("pushType");
        JSONObject messageBodyJsonObject=  (JSONObject) JSONObject.parse(jsonObject.getString("messageBody"));
        //消息体

        Transactionlog transactionlog=new Transactionlog();
        //Transactionlog transactionlog = (Transactionlog) JSONUtils.jsonToBean(jsonObjStr, Transactionlog.class);
        transactionlog.setProducer(messageBodyJsonObject.getString("producer"));
        transactionlog.setConsumer(messageBodyJsonObject.getString("consumer"));
        transactionlog.setId(messageBodyJsonObject.getString("id"));
        transactionlog.setMessageBody(jsonObject.getString("messageBody"));

        switch (pushType) {
            //0,消息类型为待确认 保存消息类型
            case 0:
                saveMessAge(transactionlog);
                break;
            //1,消息类型为已发送 同时将消息发送给下游服务 MQ。
            case 1:
                sendMq(transactionlog);
                break;
            // 2 已完成
            case 2:
                updateMessAge(transactionlog);
                break;
            default:
                break;
        }
    }

    /**
     * 保存数据
     */
    public void saveMessAge(Transactionlog transactionlog) {
        transactionlog.setCreateTime(new Date());
        transactionlog.setType(MessageType.WAITINGCONFIRM.getType());
        transactionlog.setStatus(0);
        transactionlogService.save(transactionlog);
    }

    /**
     * 已完成
     */
    public void updateMessAge( Transactionlog transactionlog) {
        try {
            Transactionlog transactionlogUp = transactionlogService.getList(transactionlog).get(0);
            if(transactionlogUp!=null)
            {
                transactionlogUp.setType(MessageType.COMPLETED.getType());
                transactionlogService.update(transactionlogUp);
            }
        }catch (Exception ex)
        {
            logger.error("更新事务已完成 报错：%s",ex.getMessage());
        }
    }

    /**
     * 更新状态 已发送&&发送消息
     */
    public void sendMq(Transactionlog transactionlog) {
        Transactionlog transactionlogUp = (Transactionlog) transactionlogService.getList(transactionlog).get(0);
        transactionlogUp.setType(MessageType.SENDED.getType());
        if (transactionlogService.update(transactionlogUp)) {
            //更新状态 已发送
            bus.AddMessAgeByQueue(new IBus() {
                @Override
                public void doQueueHandle(QueueHandler queueManager) {
                    try {
                        for (int i = 0; i < 1; i++) {
                            PushInfo info = new PushInfo();
                            info.setMessageBody(i);
                            info.setJpushTime(new Date());
                            if (queueManager.AddProducerMQ(transactionlogUp.getConsumer(), info)) {
                                System.out.println("发送成功");
                                logger.info(String.format("发送成功 生产者：%s 消费者：%s 消息体：%s",transactionlogUp.getProducer(),transactionlogUp.getConsumer(),transactionlogUp.getMessageBody().toString()));
                            } else {
                                logger.info(String.format("发送失败 生产者：%s 消费者：%s 消息体：%s",transactionlogUp.getProducer(),transactionlogUp.getConsumer(),transactionlogUp.getMessageBody().toString()));
                            }
                        }
                    } catch (Exception e) {
                        logger.error(String.format("消息ID：%s 失败原因:%s",transactionlogUp.getId(),e.getMessage()));
                    }
                }
            });
        }
    }
}

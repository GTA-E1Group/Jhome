package com.service.dao.impl;

import com.bracket.common.ToolKit.StringUtil;
import com.service.dao.TransactionLogDao;
import com.service.model.Transactionlog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
 * //              ``::::::::::::::::
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
 * @description:
 * @author: Daxv
 * @create: 2020-09-17 10:21
 **/
@Repository
public class TransactionlogDImpl implements TransactionLogDao {
    private static final Logger logger = LoggerFactory.getLogger(TransactionlogDImpl.class);
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Transactionlog Transactionlog) {
        try {
            String sql = " INSERT INTO `transaction_log`(`id`, `type`, `status`, `messageBody`, `producer`, `consumer`, `createTime`) VALUES (?, ?, ?, ?, ?, ?, ?);";
            return jdbcTemplate.update(sql,
                    Transactionlog.getId(),
                    Transactionlog.getType(),
                    Transactionlog.getStatus(),
                    Transactionlog.getMessageBody(),
                    Transactionlog.getProducer(),
                    Transactionlog.getConsumer(),
                    Transactionlog.getCreateTime()
            ) > 0;
        } catch (Exception ex) {
            logger.info(String.format("新增失败，失败原因：%s", ex.getMessage()));
            return false;
        }
    }

    @Override
    public boolean update(Transactionlog Transactionlog) {
        try {

            String sql = "UPDATE `transaction_log` SET `type` = ?, `status` = ?, `messageBody` = ?, `producer` = ?, `consumer` = ?, `createTime` = ?" +
                    " WHERE `Id` = ?; ";

            return jdbcTemplate.update(sql,
                    Transactionlog.getType(),
                    Transactionlog.getStatus(),
                    Transactionlog.getMessageBody(),
                    Transactionlog.getProducer(),
                    Transactionlog.getConsumer(),
                    Transactionlog.getCreateTime(),
                    Transactionlog.getId()) > 0;
        } catch (Exception ex) {
            logger.info(String.format("修改失败，失败原因：%s", ex.getMessage()));
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            String sql = "delete from transactionLog where id=?";
            return jdbcTemplate.update(sql, id) > 0;
        } catch (Exception ex) {
            logger.info("新增失败，失败原因：%s", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Transactionlog> findAll() {
        try {
            String sql = "select * from transaction_log";
            List<Transactionlog> sysConfigProperties = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Transactionlog.class));
            return sysConfigProperties;
        } catch (Exception ex) {
            logger.info(String.format("查询失败，失败原因：%s", ex.getMessage()));
            return null;
        }
    }

    @Override
    public List<Transactionlog> getList(Transactionlog Transactionlog) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from transaction_log where 1=1 and type!=2 ");
            if (Transactionlog!=null&& StringUtil.isNotBlank(Transactionlog.getId())) {
                stringBuilder.append(String.format(" and id='%s'", Transactionlog.getId()));
            }

            List<Transactionlog> sysConfigProperties = jdbcTemplate.query(stringBuilder.toString(), new BeanPropertyRowMapper(Transactionlog.class));
            return sysConfigProperties;
        } catch (Exception ex) {
            logger.info(String.format("查询失败，失败原因：%s", ex.getMessage()));
            return null;
        }
    }
}

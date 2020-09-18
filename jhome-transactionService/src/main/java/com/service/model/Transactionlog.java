package com.service.model;


import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


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
 * @description: 对象
 * @author: 1
 * @create: 2020-09-17
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Transactionlog对象", description = "")
public class Transactionlog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    @ApiModelProperty(value = "0：待确认 1：已发送 2：已完成")
    private Integer type;
    @ApiModelProperty(value = "0 正常 1删除")
    private Integer status;

    @ApiModelProperty(value = "消息体")
    private String messageBody;

    @ApiModelProperty(value = "生产者服务")
    private String producer;

    @ApiModelProperty(value = "消费者服务")
    private String consumer;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString() {
        return "Transactionlog{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", messageBody=" + messageBody +
                ", producer=" + producer +
                ", consumer=" + consumer +
                ", createTime=" + createTime +
                "}";
    }
}
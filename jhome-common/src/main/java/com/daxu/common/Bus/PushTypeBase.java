package com.daxu.common.Bus;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有消息实体继承此类
 */
public abstract class PushTypeBase implements Serializable {
    private int pushType;
    private Date jpushTime;
    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public Date getJpushTime() {
        return new Date();
    }

    public void setJpushTime(Date jpushTime) {
        jpushTime = jpushTime;
    }

}

package com.shiro.common.session;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * 自定义ShiroSession
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class ShiroSession extends SimpleSession {

    private  Serializable id;
    private  Date startTimestamp;
    private  Date stopTimestamp;
    private  Date lastAccessTime;
    private  long timeout;
    private  boolean expired;
    private  String host;
    private  Map<Object, Object> attributes;

    public  ShiroSession()
    {

    }
    /**
     *  除lastAccessTime以外其他字段发生改变时为true
     */
    private boolean isChanged;


    public ShiroSession(String host) {
        super(host);
        this.timeout = 1800000L;
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
        this.setChanged(true);
    }

    @Override
    public void setId(Serializable id) {
        super.setId(id);
        this.setChanged(true);
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        super.setStartTimestamp(startTimestamp);
        this.setChanged(true);
    }
    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        this.setChanged(true);
    }
    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        super.setLastAccessTime(lastAccessTime);
        this.setChanged(true);

    }

    @Override
    public void setExpired(boolean expired) {
        super.setExpired(expired);
        this.setChanged(true);
    }
    @Override
    public void setTimeout(long timeout) {
        super.setTimeout(timeout);
        this.setChanged(true);
    }
    @Override
    public void setHost(String host) {
        super.setHost(host);
        this.setChanged(true);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
        this.setChanged(true);
    }
    @Override
    public Object removeAttribute(Object key) {
        this.setChanged(true);
        return super.removeAttribute(key);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        super.stop();
        this.setChanged(true);
    }

    /**
     * 设置过期
     */
    @Override
    protected void expire() {
        super.stop();
        this.setExpired(true);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    protected boolean isTimedOut() {
        return super.isTimedOut();
    }



    public boolean isChanged() {
        return this.isChanged;
    }
    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj);
    }

    @Override
    protected boolean onEquals(SimpleSession ss) {
        return this.onEquals(ss);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

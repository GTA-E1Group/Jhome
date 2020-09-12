package com.nettyService.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author : Daxv
 * @date : 16:30 2020/5/15 0015
 */
public interface ChatService {
    public void register(JSONObject param, ChannelHandlerContext ctx);

    public void singleSend(JSONObject param, ChannelHandlerContext ctx);

    public void singleSendByRemotely(String param);

    public void groupSend(JSONObject param, ChannelHandlerContext ctx);

    public void groupSendByRemotely(String param);


    public void FileMsgSingleSend(JSONObject param, ChannelHandlerContext ctx);

    public void FileMsgGroupSend(JSONObject param, ChannelHandlerContext ctx);

    public void remove(ChannelHandlerContext ctx);

    public void typeError(ChannelHandlerContext ctx);

    public String getOnlineUserList();

}

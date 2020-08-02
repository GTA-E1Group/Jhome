package com.nettyService.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.daxu.common.Bus.ResponseJson;
import com.daxu.common.ToolKit.JSONUtils;
import com.nettyService.Model.ChatType;
import com.nettyService.Model.GroupInfo;
import com.nettyService.Service.ChatService;
import com.nettyService.Util.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : Daxv
 * @date : 16:31 2020/5/15 0015
 */

@Service
public class ChatServiceImpl implements ChatService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 注册
     *
     * @param param
     * @param ctx
     */
    @Override
    public void register(JSONObject param, ChannelHandlerContext ctx) {
        try {
            String userId = (String) param.getString("userId");
            Constant.onlineUserMap.put(userId, ctx);
            String responseJson = new ResponseJson().success("已成功注册到服务中心")
                    .setData("type", ChatType.REGISTER)
                    .toString();
            sendMessage(ctx, responseJson);
        } catch (Exception ex) {
            logger.debug("register失败：%s", ex.getMessage().toString());
        }
    }



    /**
     * 单次发送
     *
     * @param param
     * @param ctx
     */
    @Override
    public void singleSend(JSONObject param, ChannelHandlerContext ctx) {
        try {
            String fromUserId = (String) param.getString("fromUserId");
            String toUserId = (String) param.getString("toUserId");
            String content = (String) param.getString("content");
            ChannelHandlerContext toUserCtx = Constant.onlineUserMap.get(toUserId);
            if (toUserCtx == null) {
                String responseJson = new ResponseJson()
                        .success("来自服务器的消息")
                        .setData("fromUserId", fromUserId)
                        .setData("toUserId", toUserId)
                        .setData("content", content)
                        .toString();
                sendMessage(ctx, responseJson);
            } else {
                String responseJson = new ResponseJson()
                        .error("用户不在线！")
                        .toString();
                sendMessage(ctx, responseJson);
            }
        } catch (Exception ex) {
            logger.debug("singleSend失败：%s", ex.getMessage().toString());

        }

    }

    /**
     * 分组发送
     *
     * @param param
     * @param ctx
     */
    @Override
    public void groupSend(JSONObject param, ChannelHandlerContext ctx) {
        try {
            String fromUserId = (String) param.getString("fromUserId");
            String toGroupId = (String) param.getString("toGroupId");
            String content = (String) param.getString("content");

            GroupInfo groupInfo = Constant.groupInfoMap.get(toGroupId);
            if (groupInfo == null) {
                String responseJson = new ResponseJson().error("该群id不存在").toString();
                sendMessage(ctx, responseJson);
            } else {
                String responseJson = new ResponseJson().success("来自服务器的消息")
                        .setData("fromUserId", fromUserId)
                        .setData("toGroupId", toGroupId)
                        .setData("content", content)
                        .setData("type", ChatType.GROUP_SENDING)
                        .toString();
                groupInfo.getMembers().stream().forEach(c -> {
                    ChannelHandlerContext toCtx = Constant.onlineUserMap.get(c.getUserId());
                    if (toCtx != null && !c.getUserId().equals(fromUserId)) {
                        sendMessage(toCtx, responseJson);
                    }
                });

            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }



    /**
     * 文件发送
     *
     * @param param
     * @param ctx
     */
    @Override
    public void FileMsgSingleSend(JSONObject param, ChannelHandlerContext ctx) {
        try {
            String fromUserId = (String) param.getString("fromUserId");
            String toUserId = (String) param.getString("toUserId");
            String originalFilename = (String) param.getString("originalFilename");//文件名称
            String fileSize = (String) param.getString("fileSize");
            String fileUrl = (String) param.getString("fileUrl");
            ChannelHandlerContext toUserCtx = Constant.onlineUserMap.get(toUserId);
            if (toUserCtx == null) {
                String responseJson = new ResponseJson()
                        .error(MessageFormat.format("userId为 {0} 的用户没有登录！", toUserId))
                        .toString();
                sendMessage(ctx, responseJson);
            } else {
                String responseJson = new ResponseJson().success("来自服务器的消息")
                        .setData("fromUserId", fromUserId)
                        .setData("toUserId", toUserId)
                        .setData("originalFilename", originalFilename)
                        .setData("fileSize", fileSize)
                        .setData("fileUrl", fileUrl)
                        .toString();
                sendMessage(ctx, responseJson);
            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }


    /**
     * 文件分组发送
     *
     * @param param
     * @param ctx
     */
    @Override
    public void FileMsgGroupSend(JSONObject param, ChannelHandlerContext ctx) {
        try {
            String fromUserId = (String) param.getString("fromUserId");
            String toGroupId = (String) param.getString("toGroupId");
            String originalFilename = (String) param.getString("originalFilename");
            String fileSize = (String) param.getString("fileSize");
            String fileUrl = (String) param.getString("fileUrl");
            GroupInfo groupInfo = Constant.groupInfoMap.get(toGroupId);
            if (groupInfo == null) {
                String responseJson = new ResponseJson().error("该群id不存在").toString();
                sendMessage(ctx, responseJson);
            } else {
                String responseJson = new ResponseJson().success("来自服务器的消息")
                        .setData("fromUserId", fromUserId)
                        .setData("toGroupId", toGroupId)
                        .setData("originalFilename", originalFilename)
                        .setData("fileSize", fileSize)
                        .setData("fileUrl", fileUrl)
                        .setData("type", ChatType.FILE_MSG_GROUP_SENDING)
                        .toString();
                groupInfo.getMembers().stream()
                        .forEach(member -> {
                            ChannelHandlerContext toCtx = Constant.onlineUserMap.get(member.getUserId());
                            if (toCtx != null && !member.getUserId().equals(fromUserId)) {
                                sendMessage(toCtx, responseJson);
                            }
                        });
            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }

    /**
     * 下线
     *
     * @param ctx
     */
    @Override
    public void remove(ChannelHandlerContext ctx) {
        try {
            Iterator<Map.Entry<String, ChannelHandlerContext>> iterator = Constant.onlineUserMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ChannelHandlerContext> entry = iterator.next();
                if (entry.getValue() == ctx) {
                    logger.info("正在移除握手实例...");
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(new ResponseJson().success("移除握手...").toString()));
                    Constant.webSocketHandshakerMap.remove(ctx.channel().id().asLongText());
                    logger.info(MessageFormat.format("已移除握手实例，当前握手实例总数为：{0}"
                            , Constant.webSocketHandshakerMap.size()));
                    iterator.remove();
                    logger.info(MessageFormat.format("userId为 {0} 的用户已退，当前在线人数为：{1}"
                            , entry.getKey(), Constant.onlineUserMap.size()));
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(new ResponseJson().success("移除完成...").toString()));
                    break;
                }
            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }

    /**
     * 类型错误
     *
     * @param ctx
     */
    @Override
    public void typeError(ChannelHandlerContext ctx) {
        try {
            String responseJson = new ResponseJson()
                    .error("该类型不存在！")
                    .toString();
            sendMessage(ctx, responseJson);
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }

    /**
     * 获取在线用户列表
     * @return
     */
    @Override
    public String getOnlineUserList() {
        return JSONUtils.beanToJson(Constant.onlineUserMap);
    }

    /**
     * 单次发送（远程调用）
     * @param json
     * {"fromUserId":"","toUserId":"","content":}
     */
    @Override
    public void singleSendByRemotely(String json) {
        try {
            JSONObject param = JSONObject.parseObject(json);
            String fromUserId = (String) param.getString("fromUserId");
            String toUserId = (String) param.getString("toUserId");
            String content = (String) param.getString("content");
            ChannelHandlerContext ctx = Constant.onlineUserMap.get(toUserId);
            if (ctx != null) {
                String responseJson = new ResponseJson()
                        .success("来自服务器的消息")
                        .setValue("data", param)
                        .toString();
                sendMessage(ctx, responseJson);
            } else {
                String responseJson = new ResponseJson()
                        .error("用户不在线！")
                        .toString();
                sendMessage(ctx, responseJson);
            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }

    }

    /**
     * 分组发送（远程调用）
     * @param json
     * {"fromUserId":"","toGroupId":"","content":}
     */
    @Override
    public void groupSendByRemotely(String json) {
        try {
            JSONObject param = JSONObject.parseObject(json);
            String fromUserId = (String) param.getString("fromUserId");
            String toGroupId = (String) param.getString("toGroupId");
            String content = (String) param.getString("content");
            ChannelHandlerContext fromCtx = Constant.onlineUserMap.get(fromUserId);
            GroupInfo groupInfo = Constant.groupInfoMap.get(toGroupId);
            if (groupInfo == null) {
                String responseJson = new ResponseJson().error("该群id不存在").toString();
                sendMessage(fromCtx, responseJson);
            } else {
                String responseJson = new ResponseJson().success()
                        .setData("fromUserId", fromUserId)
                        .setData("toGroupId", toGroupId)
                        .setData("content", content)
                        .setData("type", ChatType.GROUP_SENDING)
                        .toString();
                groupInfo.getMembers().stream().forEach(c -> {
                    ChannelHandlerContext toCtx = Constant.onlineUserMap.get(c.getUserId());
                    if (toCtx != null && !c.getUserId().equals(fromUserId)) {
                        sendMessage(toCtx, responseJson);
                    }
                });
            }
        } catch (Exception ex) {
            logger.debug("groupSend失败：%s", ex.getMessage().toString());
        }
    }

    /**
     * 发送信息
     *
     * @param ctx
     * @param responseJson
     */
    private void sendMessage(ChannelHandlerContext ctx, String responseJson) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }
}

package com.daxu.common.Bus;
 

/**
 * 推送实体
 *
 */
public class PushInfo extends PushTypeBase {
	public Object messageBody;

	public Object getMessageBody() {
		return messageBody;
	} 
	public void setMessageBody(Object messageBody) {
		this.messageBody = messageBody;
	}
}

package com.bracket.common.ServerSocket;

import com.bracket.common.Bus.PushTypeBase;
import com.bracket.common.Bus.PushTypeBase;


/**
 * @author xu.da1
 * 接收方法
 */
public interface OnRecevieMsgListener {
	public void onReceive(PushTypeBase msg);
}

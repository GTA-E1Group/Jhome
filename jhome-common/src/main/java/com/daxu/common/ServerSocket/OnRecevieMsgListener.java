package com.daxu.common.ServerSocket;

import com.daxu.common.Bus.PushTypeBase;


/**
 * @author xu.da1
 * 接收方法
 */
public interface OnRecevieMsgListener {
	public void onReceive(PushTypeBase msg);
}

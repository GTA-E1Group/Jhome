package com.bracket.common.Queue;

public class Config {
	private String HostName;
	private String UserName;
	private String PassWord;
	private String VirtualHost;
	
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Config(String hostName, String userName, String passWord,
			String virtualHost) {
		super();
		HostName = hostName;
		UserName = userName;
		PassWord = passWord;
		VirtualHost = virtualHost;
	}
	public String getHostName() {
		return HostName;
	}
	public void setHostName(String hostName) {
		HostName = hostName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getVirtualHost() {
		return VirtualHost;
	}
	public void setVirtualHost(String virtualHost) {
		VirtualHost = virtualHost;
	}

	@Override
	public String toString() {
		return "config{" +
				"HostName='" + HostName + '\'' +
				", UserName='" + UserName + '\'' +
				", PassWord='" + PassWord + '\'' +
				", VirtualHost='" + VirtualHost + '\'' +
				'}';
	}
}



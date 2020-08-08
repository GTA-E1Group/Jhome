package com.bracket.common.Bus;
 

public class SocketInfo{
 
	private String to;
	private String messAge;
	public String getTo() {
		return to;
	}
	
	public SocketInfo(String to, String messAge) {
		super();
		this.to = to;
		this.messAge = messAge;
	}

	public void setTo(String to) {
		this.to = to;
	}
	public SocketInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessAge() {
		return messAge;
	}
	public void setMessAge(String messAge) {
		this.messAge = messAge;
	}
	
}

package com.bracket.common.Vo;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class UserEntity { 
	private int id;
	@Column
	private String account;
	@Column
	private String password;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public UserEntity(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public UserEntity(int id, String account, String password) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

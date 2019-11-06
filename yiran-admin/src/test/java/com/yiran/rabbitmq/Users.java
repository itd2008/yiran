package com.yiran.rabbitmq;

import java.io.Serializable;

public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5849942996227369720L;
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}

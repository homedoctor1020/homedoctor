package com.dw.model;
/**
 *  管理员类
 * @author DY1101shaoyuxian
 *
 */
public class User {
	private int id;//管理员id
	private String username;//用户名
	private String password;//密码

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

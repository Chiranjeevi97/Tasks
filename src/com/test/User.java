package com.test;

public class User {

	private String fullname;
	private String username;
	private String password;
	private String access_level;
	
	public String getFullname() {
		return fullname;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public String getAccessLevel() {
		return access_level;
	}
	
	public void setFullname(String _fullname) {
		this.fullname = _fullname;
	}
	public void setUsername(String _username) {
		this.username = _username;
	}
	public void setPassword(String _password) {
		this.password = _password;
	}
	public void setAccessLevel(String _access_level) {
		this.access_level = _access_level;
	}
	
}

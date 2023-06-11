package com.ssafy.trip.user.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;

public class User implements Serializable{
	
	@ApiParam(value="user userid", required = true)
	private String userid;
	@ApiParam(value="user password", required = true)
	private String userpwd;
	@ApiParam(value="user name", required = true)
	private String name;
	@ApiParam(value="user email", required = true)
	private String email;
	@ApiParam(value="user nickname", required = true)
	private String nickname;
	

	public User() {
		super();
	}

	public User(String userid, String userpwd, String name, String email, String nickname) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
	}





	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userid=").append(userid).append(", userpwd=").append(userpwd).append(", name=").append(name)
				.append(", email=").append(email).append(", nickname=").append(nickname).append("]");
		return builder.toString();
	}



}

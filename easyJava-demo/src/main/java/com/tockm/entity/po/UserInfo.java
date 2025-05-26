package com.tockm.entity.po;

import java.io.Serializable;


/**
 *@Description: 
 *@date: 2025/05/26
 */
public class UserInfo implements Serializable {
	private Integer userId;

	private String username;

	private String email;

	private String password;


	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	@Override
	public String toString() {
		return "userId:"+(userId== null?"空" : userId)+",username:"+(username== null?"空" : username)+",email:"+(email== null?"空" : email)+",password:"+(password== null?"空" : password);
	}
}
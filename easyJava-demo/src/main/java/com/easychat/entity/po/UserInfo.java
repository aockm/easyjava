package com.easychat.entity.po;

import java.io.Serializable;


/**
 *@Description: 
 *@date: 2025/05/25
 */
public class UserInfo implements Serializable {
	private String email;

	private String password;


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
		return "email:"+(email== null?"空" : email)+",password:"+(password== null?"空" : password);
	}
}
package com.easychat.entity.po;

import java.io.Serializable;


/**
 *@Description: 联系人
 *@date: 2025/05/12
 */
public class ChatSession implements Serializable {
	/**
	 * 会话ID
	 */
	private String sessionId;

	/**
	 * 最后接收的消息
	 */
	private String lastMessage;

	/**
	 * 最后接收的消息时间毫秒
	 */
	private Long lastReceiveTime;


	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}

	public String getSessionId(){
		return this.sessionId;
	}

	public void setLastMessage(String lastMessage){
		this.lastMessage = lastMessage;
	}

	public String getLastMessage(){
		return this.lastMessage;
	}

	public void setLastReceiveTime(Long lastReceiveTime){
		this.lastReceiveTime = lastReceiveTime;
	}

	public Long getLastReceiveTime(){
		return this.lastReceiveTime;
	}

	@Override
	public String toString() {
		return "会话ID:"+(sessionId== null?"空" : sessionId)+",最后接收的消息:"+(lastMessage== null?"空" : lastMessage)+",最后接收的消息时间毫秒:"+(lastReceiveTime== null?"空" : lastReceiveTime);
	}
}
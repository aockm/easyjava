package com.easychat.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easychat.enums.DateTimePatternEnum;
import com.easychat.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 *@Description: 
 *@date: 2025/05/12
 */
public class GroupInfo implements Serializable {
	/**
	 * 群ID
	 */
	private String groupId;

	/**
	 * 群组名
	 */
	private String groupName;

	/**
	 * 群主ID
	 */
	private String groupOwnerId;

	/**
	 * 创建时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date crateTime;

	/**
	 * 群公告
	 */
	private String groupNotice;

	/**
	 * 0:直接加入 1:管理员同意
	 */
	private Integer joinType;

	/**
	 * 状态 1:正常 0:解散
	 */
	@JsonIgnore
	private Integer status;


	public void setGroupId(String groupId){
		this.groupId = groupId;
	}

	public String getGroupId(){
		return this.groupId;
	}

	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	public String getGroupName(){
		return this.groupName;
	}

	public void setGroupOwnerId(String groupOwnerId){
		this.groupOwnerId = groupOwnerId;
	}

	public String getGroupOwnerId(){
		return this.groupOwnerId;
	}

	public void setCrateTime(Date crateTime){
		this.crateTime = crateTime;
	}

	public Date getCrateTime(){
		return this.crateTime;
	}

	public void setGroupNotice(String groupNotice){
		this.groupNotice = groupNotice;
	}

	public String getGroupNotice(){
		return this.groupNotice;
	}

	public void setJoinType(Integer joinType){
		this.joinType = joinType;
	}

	public Integer getJoinType(){
		return this.joinType;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	@Override
	public String toString() {
		return "群ID:"+(groupId== null?"空" : groupId)+",群组名:"+(groupName== null?"空" : groupName)+",群主ID:"+(groupOwnerId== null?"空" : groupOwnerId)+",创建时间:"+(crateTime== null?"空" : DateUtils.format(crateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+",群公告:"+(groupNotice== null?"空" : groupNotice)+",0:直接加入 1:管理员同意:"+(joinType== null?"空" : joinType)+",状态 1:正常 0:解散:"+(status== null?"空" : status);
	}
}
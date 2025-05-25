package com.easychat.entity.query;

import java.util.Date;


/**
 *@Description: 查询
 *@date: 2025/05/12
 */
public class GroupInfoQuery extends BaseQuery {
	/**
	 * 群ID
	 */
	private String groupId;

	private String groupIdFuzzy;

	/**
	 * 群组名
	 */
	private String groupName;

	private String groupNameFuzzy;

	/**
	 * 群主ID
	 */
	private String groupOwnerId;

	private String groupOwnerIdFuzzy;

	/**
	 * 创建时间
	 */
	private Date crateTime;

	private String crateTimeStart;

	private String crateTimeEnd;

	/**
	 * 群公告
	 */
	private String groupNotice;

	private String groupNoticeFuzzy;

	/**
	 * 0:直接加入 1:管理员同意
	 */
	private Integer joinType;

	/**
	 * 状态 1:正常 0:解散
	 */
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

	public void setGroupIdFuzzy(String groupIdFuzzy){
		this.groupIdFuzzy = groupIdFuzzy;
	}

	public String getGroupIdFuzzy(){
		return this.groupIdFuzzy;
	}

	public void setGroupNameFuzzy(String groupNameFuzzy){
		this.groupNameFuzzy = groupNameFuzzy;
	}

	public String getGroupNameFuzzy(){
		return this.groupNameFuzzy;
	}

	public void setGroupOwnerIdFuzzy(String groupOwnerIdFuzzy){
		this.groupOwnerIdFuzzy = groupOwnerIdFuzzy;
	}

	public String getGroupOwnerIdFuzzy(){
		return this.groupOwnerIdFuzzy;
	}

	public void setGroupNoticeFuzzy(String groupNoticeFuzzy){
		this.groupNoticeFuzzy = groupNoticeFuzzy;
	}

	public String getGroupNoticeFuzzy(){
		return this.groupNoticeFuzzy;
	}

}
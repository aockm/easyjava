package com.easychat.entity.query;

import java.util.Date;


/**
 *@Description: 查询
 *@date: 2025/04/21
 */
public class WxUserQuery extends BaseQuery {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String name;

	private String nameFuzzy;

	/**
	 * 解析次数
	 */
	private Integer videoNumber;

	/**
	 * 累计签到次数
	 */
	private Integer signInSum;

	/**
	 * 最后签到时间
	 */
	private Date endSignInTime;

	private String endSignInTimeStart;

	private String endSignInTimeEnd;

	/**
	 * 用户openId
	 */
	private String openId;

	private String openIdFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 最后解析时间
	 */
	private Date lastParsingTime;

	private String lastParsingTimeStart;

	private String lastParsingTimeEnd;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setVideoNumber(Integer videoNumber){
		this.videoNumber = videoNumber;
	}

	public Integer getVideoNumber(){
		return this.videoNumber;
	}

	public void setSignInSum(Integer signInSum){
		this.signInSum = signInSum;
	}

	public Integer getSignInSum(){
		return this.signInSum;
	}

	public void setEndSignInTime(Date endSignInTime){
		this.endSignInTime = endSignInTime;
	}

	public Date getEndSignInTime(){
		return this.endSignInTime;
	}

	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setLastParsingTime(Date lastParsingTime){
		this.lastParsingTime = lastParsingTime;
	}

	public Date getLastParsingTime(){
		return this.lastParsingTime;
	}

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

	public void setOpenIdFuzzy(String openIdFuzzy){
		this.openIdFuzzy = openIdFuzzy;
	}

	public String getOpenIdFuzzy(){
		return this.openIdFuzzy;
	}

}
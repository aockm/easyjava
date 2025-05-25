package com.easychat.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easychat.enums.DateTimePatternEnum;
import com.easychat.utils.DateUtils;


/**
 *@Description: 
 *@date: 2025/04/21
 */
public class WxUser implements Serializable {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String name;

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
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endSignInTime;

	/**
	 * 用户openId
	 */
	private String openId;

	/**
	 * 创建时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 最后解析时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastParsingTime;


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

	@Override
	public String toString() {
		return "主键ID:"+(id== null?"空" : id)+",用户名:"+(name== null?"空" : name)+",解析次数:"+(videoNumber== null?"空" : videoNumber)+",累计签到次数:"+(signInSum== null?"空" : signInSum)+",最后签到时间:"+(endSignInTime== null?"空" : DateUtils.format(endSignInTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+",用户openId:"+(openId== null?"空" : openId)+",创建时间:"+(createTime== null?"空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+",最后解析时间:"+(lastParsingTime== null?"空" : DateUtils.format(lastParsingTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
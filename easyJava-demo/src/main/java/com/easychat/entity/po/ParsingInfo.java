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
public class ParsingInfo implements Serializable {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * openId
	 */
	private String userOpenId;

	/**
	 * 视频链接
	 */
	private String downloadUrl;

	/**
	 * 视频标题
	 */
	private String title;

	/**
	 * 视频作者
	 */
	private String author;

	/**
	 * 视频封面地址
	 */
	private String cover;

	/**
	 * 创建时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUserOpenId(String userOpenId){
		this.userOpenId = userOpenId;
	}

	public String getUserOpenId(){
		return this.userOpenId;
	}

	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}

	public String getDownloadUrl(){
		return this.downloadUrl;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return this.author;
	}

	public void setCover(String cover){
		this.cover = cover;
	}

	public String getCover(){
		return this.cover;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	@Override
	public String toString() {
		return "主键ID:"+(id== null?"空" : id)+",openId:"+(userOpenId== null?"空" : userOpenId)+",视频链接:"+(downloadUrl== null?"空" : downloadUrl)+",视频标题:"+(title== null?"空" : title)+",视频作者:"+(author== null?"空" : author)+",视频封面地址:"+(cover== null?"空" : cover)+",创建时间:"+(createTime== null?"空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
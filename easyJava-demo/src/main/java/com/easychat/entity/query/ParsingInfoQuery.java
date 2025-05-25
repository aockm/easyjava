package com.easychat.entity.query;

import java.util.Date;


/**
 *@Description: 查询
 *@date: 2025/04/21
 */
public class ParsingInfoQuery extends BaseQuery {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * openId
	 */
	private String userOpenId;

	private String userOpenIdFuzzy;

	/**
	 * 视频链接
	 */
	private String downloadUrl;

	private String downloadUrlFuzzy;

	/**
	 * 视频标题
	 */
	private String title;

	private String titleFuzzy;

	/**
	 * 视频作者
	 */
	private String author;

	private String authorFuzzy;

	/**
	 * 视频封面地址
	 */
	private String cover;

	private String coverFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;


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

	public void setUserOpenIdFuzzy(String userOpenIdFuzzy){
		this.userOpenIdFuzzy = userOpenIdFuzzy;
	}

	public String getUserOpenIdFuzzy(){
		return this.userOpenIdFuzzy;
	}

	public void setDownloadUrlFuzzy(String downloadUrlFuzzy){
		this.downloadUrlFuzzy = downloadUrlFuzzy;
	}

	public String getDownloadUrlFuzzy(){
		return this.downloadUrlFuzzy;
	}

	public void setTitleFuzzy(String titleFuzzy){
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy(){
		return this.titleFuzzy;
	}

	public void setAuthorFuzzy(String authorFuzzy){
		this.authorFuzzy = authorFuzzy;
	}

	public String getAuthorFuzzy(){
		return this.authorFuzzy;
	}

	public void setCoverFuzzy(String coverFuzzy){
		this.coverFuzzy = coverFuzzy;
	}

	public String getCoverFuzzy(){
		return this.coverFuzzy;
	}

}
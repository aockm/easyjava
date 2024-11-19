package com.tockm.entity.query;

import java.util.Date;


/**
 *@Description: 查询
 *@date: 2024/11/19
 */
public class NameInfoQuery extends BaseQuery {
	private Integer id;

	private String name;

	private String nameFuzzy;

	private Date createTime;

	private String title;

	private String titleFuzzy;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

	public void setTitleFuzzy(String titleFuzzy){
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy(){
		return this.titleFuzzy;
	}

}
package com.tockm.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.tockm.enums.DateTimePatternEnum;
import com.tockm.utils.DateUtils;


/**
 *@Description: 
 *@date: 2024/11/19
 */
public class NameInfo implements Serializable {
	private Integer id;

	private String name;

	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String title;


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

	@Override
	public String toString() {
		return "id:"+(id== null?"空" : id)+",name:"+(name== null?"空" : name)+",createTime:"+(createTime== null?"空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+",title:"+(title== null?"空" : title);
	}
}
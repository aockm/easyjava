package com.tockm.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.tockm.enums.DateTimePatternEnum;
import com.tockm.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 *@Description: 手机号码
 *@date: 2024/11/17
 */
public class PhoneInfo implements Serializable {
	private Integer id;

	private String phone;

	private String province;

	private String city;

	@JsonIgnore
	private String sp;

	private String name;

	private String sex;

	/**
	 * 创建时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JsonFormat (pattern = "yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date createDate;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return this.province;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return this.city;
	}

	public void setSp(String sp){
		this.sp = sp;
	}

	public String getSp(){
		return this.sp;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return this.sex;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getCreateDate(){
		return this.createDate;
	}

	@Override
	public String toString() {
		return "id:"+(id== null?"空" : id)+",phone:"+(phone== null?"空" : phone)+",province:"+(province== null?"空" : province)+",city:"+(city== null?"空" : city)+",sp:"+(sp== null?"空" : sp)+",name:"+(name== null?"空" : name)+",sex:"+(sex== null?"空" : sex)+",创建时间:"+(createTime== null?"空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+",createDate:"+(createDate== null?"空" : DateUtils.format(createDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
	}
}
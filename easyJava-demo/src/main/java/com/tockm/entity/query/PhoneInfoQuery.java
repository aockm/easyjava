package com.tockm.entity.query;

import java.util.Date;


/**
 *@Description: 手机号码查询
 *@date: 2024/11/03
 */
public class PhoneInfoQuery {
	private Integer id;

	private String phone;

	private String phoneFuzzy;

	private String province;

	private String provinceFuzzy;

	private String city;

	private String cityFuzzy;

	private String sp;

	private String spFuzzy;

	private String name;

	private String nameFuzzy;

	private String sex;

	private String sexFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private Date createDate;

	private String createDateStart;

	private String createDateEnd;


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

	public void setPhoneFuzzy(String phoneFuzzy){
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy(){
		return this.phoneFuzzy;
	}

	public void setProvinceFuzzy(String provinceFuzzy){
		this.provinceFuzzy = provinceFuzzy;
	}

	public String getProvinceFuzzy(){
		return this.provinceFuzzy;
	}

	public void setCityFuzzy(String cityFuzzy){
		this.cityFuzzy = cityFuzzy;
	}

	public String getCityFuzzy(){
		return this.cityFuzzy;
	}

	public void setSpFuzzy(String spFuzzy){
		this.spFuzzy = spFuzzy;
	}

	public String getSpFuzzy(){
		return this.spFuzzy;
	}

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

	public void setSexFuzzy(String sexFuzzy){
		this.sexFuzzy = sexFuzzy;
	}

	public String getSexFuzzy(){
		return this.sexFuzzy;
	}

	public void setCreateDateStart(String createDateStart){
		this.createDateStart = createDateStart;
	}

	public String getCreateDateStart(){
		return this.createDateStart;
	}

	public void setCreateDateEnd(String createDateEnd){
		this.createDateEnd = createDateEnd;
	}

	public String getCreateDateEnd(){
		return this.createDateEnd;
	}

}
package com.tockm.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *@Description: 手机号码
 *@date: 2024/10/29
 */
public class PhoneInfo implements Serializable {
	private Integer id;

	private String phone;

	private String province;

	private String city;

	private String sp;

	private String name;

	private String sex;

	/**
	 * 创建时间
	 */
	@JsonFormat (pattern = "yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date createTime;


}
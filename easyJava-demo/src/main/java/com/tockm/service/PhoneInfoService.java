package com.tockm.service;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import java.util.List;

/**
 *@Description: 手机号码Service
 *@date: 2024/11/10
 */
public interface PhoneInfoService {
	/**
	 * 根据条件查询列表
	 */
	List<PhoneInfo> findListByParam(PhoneInfoQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(PhoneInfoQuery param);
	/**
	 * 分页查询
	 */
	Integer findListByPage(PhoneInfoQuery param);
}
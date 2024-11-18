package com.tockm.service;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import java.util.List;
import com.tockm.entity.vo.PaginationResultVo;

/**
 *@Description: 手机号码Service
 *@date: 2024/11/18
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
	PaginationResultVo<PhoneInfo> findListByPage(PhoneInfoQuery param);
	/**
	 * 新增
	 */
	Integer add(PhoneInfo bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<PhoneInfo> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<PhoneInfo> bean);

	/**
	 * 根据Id查询
	 */
	PhoneInfo getPhoneInfoById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updatePhoneInfoById(PhoneInfo bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deletePhoneInfoById(Integer id);
}
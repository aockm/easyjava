package com.tockm.service;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import java.util.List;

/**
 *@Description: Service
 *@date: 2024/11/10
 */
public interface NameInfoService {
	/**
	 * 根据条件查询列表
	 */
	List<NameInfo> findListByParam(NameInfoQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(NameInfoQuery param);
	/**
	 * 分页查询
	 */
	Integer findListByPage(NameInfoQuery param);
}
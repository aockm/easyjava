package com.tockm.service;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import java.util.List;
import com.tockm.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2024/11/17
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
	PaginationResultVo<NameInfo> findListByPage(NameInfoQuery param);
}
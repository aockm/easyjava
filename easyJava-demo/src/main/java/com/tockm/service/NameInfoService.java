package com.tockm.service;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import java.util.List;
import com.tockm.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2024/11/19
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
	/**
	 * 新增
	 */
	Integer add(NameInfo bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<NameInfo> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<NameInfo> bean);

	/**
	 * 根据Id查询
	 */
	NameInfo getNameInfoById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateNameInfoById(NameInfo bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteNameInfoById(Integer id);

	/**
	 * 根据Name查询
	 */
	NameInfo getNameInfoByName(String name);

	/**
	 * 根据Name更新
	 */
	Integer updateNameInfoByName(NameInfo bean, String name);

	/**
	 * 根据Name删除
	 */
	Integer deleteNameInfoByName(String name);
}
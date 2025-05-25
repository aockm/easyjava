package com.easychat.service;
import com.easychat.entity.po.ParsingInfo;
import com.easychat.entity.query.ParsingInfoQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2025/04/21
 */
public interface ParsingInfoService {
	/**
	 * 根据条件查询列表
	 */
	List<ParsingInfo> findListByParam(ParsingInfoQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(ParsingInfoQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<ParsingInfo> findListByPage(ParsingInfoQuery param);
	/**
	 * 新增
	 */
	Integer add(ParsingInfo bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<ParsingInfo> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ParsingInfo> bean);

	/**
	 * 根据Id查询
	 */
	ParsingInfo getParsingInfoById(Long id);

	/**
	 * 根据Id更新
	 */
	Integer updateParsingInfoById(ParsingInfo bean, Long id);

	/**
	 * 根据Id删除
	 */
	Integer deleteParsingInfoById(Long id);
}
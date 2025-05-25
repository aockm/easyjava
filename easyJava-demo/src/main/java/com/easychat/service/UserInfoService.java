package com.easychat.service;
import com.easychat.entity.po.UserInfo;
import com.easychat.entity.query.UserInfoQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2025/05/25
 */
public interface UserInfoService {
	/**
	 * 根据条件查询列表
	 */
	List<UserInfo> findListByParam(UserInfoQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(UserInfoQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<UserInfo> findListByPage(UserInfoQuery param);
	/**
	 * 新增
	 */
	Integer add(UserInfo bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserInfo> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserInfo> bean);
}
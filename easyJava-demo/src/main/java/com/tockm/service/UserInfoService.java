package com.tockm.service;
import com.tockm.entity.po.UserInfo;
import com.tockm.entity.query.UserInfoQuery;
import java.util.List;
import com.tockm.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2025/05/26
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

	/**
	 * 根据UserId查询
	 */
	UserInfo getUserInfoByUserId(Integer userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateUserInfoByUserId(UserInfo bean, Integer userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoByUserId(Integer userId);
}
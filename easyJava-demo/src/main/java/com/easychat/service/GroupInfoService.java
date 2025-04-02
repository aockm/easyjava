package com.easychat.service;
import com.easychat.entity.po.GroupInfo;
import com.easychat.entity.query.GroupInfoQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2025/04/02
 */
public interface GroupInfoService {
	/**
	 * 根据条件查询列表
	 */
	List<GroupInfo> findListByParam(GroupInfoQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(GroupInfoQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<GroupInfo> findListByPage(GroupInfoQuery param);
	/**
	 * 新增
	 */
	Integer add(GroupInfo bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<GroupInfo> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<GroupInfo> bean);

	/**
	 * 根据GroupId查询
	 */
	GroupInfo getGroupInfoByGroupId(String groupId);

	/**
	 * 根据GroupId更新
	 */
	Integer updateGroupInfoByGroupId(GroupInfo bean, String groupId);

	/**
	 * 根据GroupId删除
	 */
	Integer deleteGroupInfoByGroupId(String groupId);
}
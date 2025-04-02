package com.easychat.service;
import com.easychat.entity.po.UserContactApply;
import com.easychat.entity.query.UserContactApplyQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: 联系人申请Service
 *@date: 2025/04/02
 */
public interface UserContactApplyService {
	/**
	 * 根据条件查询列表
	 */
	List<UserContactApply> findListByParam(UserContactApplyQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(UserContactApplyQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<UserContactApply> findListByPage(UserContactApplyQuery param);
	/**
	 * 新增
	 */
	Integer add(UserContactApply bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserContactApply> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserContactApply> bean);

	/**
	 * 根据ApplyId查询
	 */
	UserContactApply getUserContactApplyByApplyId(Integer applyId);

	/**
	 * 根据ApplyId更新
	 */
	Integer updateUserContactApplyByApplyId(UserContactApply bean, Integer applyId);

	/**
	 * 根据ApplyId删除
	 */
	Integer deleteUserContactApplyByApplyId(Integer applyId);

	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId查询
	 */
	UserContactApply getUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId);

	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId更新
	 */
	Integer updateUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(UserContactApply bean, String applyUserId, String receiveUserId, String contactId);

	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId删除
	 */
	Integer deleteUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId);
}
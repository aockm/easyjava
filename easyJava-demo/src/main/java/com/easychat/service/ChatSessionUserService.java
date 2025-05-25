package com.easychat.service;
import com.easychat.entity.po.ChatSessionUser;
import com.easychat.entity.query.ChatSessionUserQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: 会话用户Service
 *@date: 2025/05/12
 */
public interface ChatSessionUserService {
	/**
	 * 根据条件查询列表
	 */
	List<ChatSessionUser> findListByParam(ChatSessionUserQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(ChatSessionUserQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<ChatSessionUser> findListByPage(ChatSessionUserQuery param);
	/**
	 * 新增
	 */
	Integer add(ChatSessionUser bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<ChatSessionUser> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ChatSessionUser> bean);

	/**
	 * 根据UserIdAndContactId查询
	 */
	ChatSessionUser getChatSessionUserByUserIdAndContactId(String userId, String contactId);

	/**
	 * 根据UserIdAndContactId更新
	 */
	Integer updateChatSessionUserByUserIdAndContactId(ChatSessionUser bean, String userId, String contactId);

	/**
	 * 根据UserIdAndContactId删除
	 */
	Integer deleteChatSessionUserByUserIdAndContactId(String userId, String contactId);
}
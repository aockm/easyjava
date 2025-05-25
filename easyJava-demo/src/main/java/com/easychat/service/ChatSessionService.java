package com.easychat.service;
import com.easychat.entity.po.ChatSession;
import com.easychat.entity.query.ChatSessionQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: 联系人Service
 *@date: 2025/05/12
 */
public interface ChatSessionService {
	/**
	 * 根据条件查询列表
	 */
	List<ChatSession> findListByParam(ChatSessionQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(ChatSessionQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<ChatSession> findListByPage(ChatSessionQuery param);
	/**
	 * 新增
	 */
	Integer add(ChatSession bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<ChatSession> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ChatSession> bean);

	/**
	 * 根据SessionId查询
	 */
	ChatSession getChatSessionBySessionId(String sessionId);

	/**
	 * 根据SessionId更新
	 */
	Integer updateChatSessionBySessionId(ChatSession bean, String sessionId);

	/**
	 * 根据SessionId删除
	 */
	Integer deleteChatSessionBySessionId(String sessionId);
}
package com.easychat.service;
import com.easychat.entity.po.ChatMessage;
import com.easychat.entity.query.ChatMessageQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: 聊天消息表Service
 *@date: 2025/05/12
 */
public interface ChatMessageService {
	/**
	 * 根据条件查询列表
	 */
	List<ChatMessage> findListByParam(ChatMessageQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(ChatMessageQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<ChatMessage> findListByPage(ChatMessageQuery param);
	/**
	 * 新增
	 */
	Integer add(ChatMessage bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<ChatMessage> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ChatMessage> bean);

	/**
	 * 根据MessageId查询
	 */
	ChatMessage getChatMessageByMessageId(Long messageId);

	/**
	 * 根据MessageId更新
	 */
	Integer updateChatMessageByMessageId(ChatMessage bean, Long messageId);

	/**
	 * 根据MessageId删除
	 */
	Integer deleteChatMessageByMessageId(Long messageId);
}
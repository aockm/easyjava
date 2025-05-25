package com.easychat.controller;
import com.easychat.service.ChatMessageService;
import com.easychat.entity.po.ChatMessage;
import com.easychat.entity.query.ChatMessageQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: 聊天消息表Service
 *@date: 2025/05/12
 */
@RestController
@RequestMapping("chatMessage")
public class ChatMessageController extends ABaseController {
	@Resource
	private ChatMessageService chatMessageService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(ChatMessageQuery query){
		return getSuccessResponseVo(chatMessageService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(ChatMessage bean){
		chatMessageService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<ChatMessage> listBean){
		chatMessageService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<ChatMessage> listBean){
		chatMessageService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据MessageId查询
	 */
	@RequestMapping("getChatMessageByMessageId")
	public ResponseVo getChatMessageByMessageId(Long messageId){
		return getSuccessResponseVo(chatMessageService.getChatMessageByMessageId(messageId));
	}


	/**
	 * 根据MessageId更新
	 */
	@RequestMapping("updateChatMessageByMessageId")
	public ResponseVo updateChatMessageByMessageId(ChatMessage bean, Long messageId){
		return getSuccessResponseVo(chatMessageService.updateChatMessageByMessageId(bean,messageId));
	}


	/**
	 * 根据MessageId删除
	 */
	@RequestMapping("deleteChatMessageByMessageId")
	public ResponseVo deleteChatMessageByMessageId(Long messageId){
		return getSuccessResponseVo(chatMessageService.deleteChatMessageByMessageId(messageId));
	}

}
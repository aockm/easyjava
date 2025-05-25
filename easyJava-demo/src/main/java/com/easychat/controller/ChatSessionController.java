package com.easychat.controller;
import com.easychat.service.ChatSessionService;
import com.easychat.entity.po.ChatSession;
import com.easychat.entity.query.ChatSessionQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: 联系人Service
 *@date: 2025/05/12
 */
@RestController
@RequestMapping("chatSession")
public class ChatSessionController extends ABaseController {
	@Resource
	private ChatSessionService chatSessionService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(ChatSessionQuery query){
		return getSuccessResponseVo(chatSessionService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(ChatSession bean){
		chatSessionService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<ChatSession> listBean){
		chatSessionService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<ChatSession> listBean){
		chatSessionService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据SessionId查询
	 */
	@RequestMapping("getChatSessionBySessionId")
	public ResponseVo getChatSessionBySessionId(String sessionId){
		return getSuccessResponseVo(chatSessionService.getChatSessionBySessionId(sessionId));
	}


	/**
	 * 根据SessionId更新
	 */
	@RequestMapping("updateChatSessionBySessionId")
	public ResponseVo updateChatSessionBySessionId(ChatSession bean, String sessionId){
		return getSuccessResponseVo(chatSessionService.updateChatSessionBySessionId(bean,sessionId));
	}


	/**
	 * 根据SessionId删除
	 */
	@RequestMapping("deleteChatSessionBySessionId")
	public ResponseVo deleteChatSessionBySessionId(String sessionId){
		return getSuccessResponseVo(chatSessionService.deleteChatSessionBySessionId(sessionId));
	}

}
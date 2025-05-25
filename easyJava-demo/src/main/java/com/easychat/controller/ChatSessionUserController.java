package com.easychat.controller;
import com.easychat.service.ChatSessionUserService;
import com.easychat.entity.po.ChatSessionUser;
import com.easychat.entity.query.ChatSessionUserQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: 会话用户Service
 *@date: 2025/05/12
 */
@RestController
@RequestMapping("chatSessionUser")
public class ChatSessionUserController extends ABaseController {
	@Resource
	private ChatSessionUserService chatSessionUserService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(ChatSessionUserQuery query){
		return getSuccessResponseVo(chatSessionUserService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(ChatSessionUser bean){
		chatSessionUserService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<ChatSessionUser> listBean){
		chatSessionUserService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<ChatSessionUser> listBean){
		chatSessionUserService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据UserIdAndContactId查询
	 */
	@RequestMapping("getChatSessionUserByUserIdAndContactId")
	public ResponseVo getChatSessionUserByUserIdAndContactId(String userId, String contactId){
		return getSuccessResponseVo(chatSessionUserService.getChatSessionUserByUserIdAndContactId(userId, contactId));
	}


	/**
	 * 根据UserIdAndContactId更新
	 */
	@RequestMapping("updateChatSessionUserByUserIdAndContactId")
	public ResponseVo updateChatSessionUserByUserIdAndContactId(ChatSessionUser bean, String userId, String contactId){
		return getSuccessResponseVo(chatSessionUserService.updateChatSessionUserByUserIdAndContactId(bean,userId, contactId));
	}


	/**
	 * 根据UserIdAndContactId删除
	 */
	@RequestMapping("deleteChatSessionUserByUserIdAndContactId")
	public ResponseVo deleteChatSessionUserByUserIdAndContactId(String userId, String contactId){
		return getSuccessResponseVo(chatSessionUserService.deleteChatSessionUserByUserIdAndContactId(userId, contactId));
	}

}
package com.easychat.controller;
import com.easychat.service.UserContactApplyService;
import com.easychat.entity.po.UserContactApply;
import com.easychat.entity.query.UserContactApplyQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: 联系人申请Service
 *@date: 2025/04/02
 */
@RestController
@RequestMapping("userContactApply")
public class UserContactApplyController extends ABaseController {
	@Resource
	private UserContactApplyService userContactApplyService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(UserContactApplyQuery query){
		return getSuccessResponseVo(userContactApplyService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(UserContactApply bean){
		userContactApplyService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<UserContactApply> listBean){
		userContactApplyService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<UserContactApply> listBean){
		userContactApplyService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据ApplyId查询
	 */
	@RequestMapping("getUserContactApplyByApplyId")
	public ResponseVo getUserContactApplyByApplyId(Integer applyId){
		return getSuccessResponseVo(userContactApplyService.getUserContactApplyByApplyId(applyId));
	}


	/**
	 * 根据ApplyId更新
	 */
	@RequestMapping("updateUserContactApplyByApplyId")
	public ResponseVo updateUserContactApplyByApplyId(UserContactApply bean, Integer applyId){
		return getSuccessResponseVo(userContactApplyService.updateUserContactApplyByApplyId(bean,applyId));
	}


	/**
	 * 根据ApplyId删除
	 */
	@RequestMapping("deleteUserContactApplyByApplyId")
	public ResponseVo deleteUserContactApplyByApplyId(Integer applyId){
		return getSuccessResponseVo(userContactApplyService.deleteUserContactApplyByApplyId(applyId));
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId查询
	 */
	@RequestMapping("getUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId")
	public ResponseVo getUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId){
		return getSuccessResponseVo(userContactApplyService.getUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(applyUserId, receiveUserId, contactId));
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId更新
	 */
	@RequestMapping("updateUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId")
	public ResponseVo updateUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(UserContactApply bean, String applyUserId, String receiveUserId, String contactId){
		return getSuccessResponseVo(userContactApplyService.updateUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(bean,applyUserId, receiveUserId, contactId));
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId删除
	 */
	@RequestMapping("deleteUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId")
	public ResponseVo deleteUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId){
		return getSuccessResponseVo(userContactApplyService.deleteUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(applyUserId, receiveUserId, contactId));
	}

}
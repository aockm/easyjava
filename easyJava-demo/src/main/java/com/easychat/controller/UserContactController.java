package com.easychat.controller;
import com.easychat.service.UserContactService;
import com.easychat.entity.po.UserContact;
import com.easychat.entity.query.UserContactQuery;
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
@RequestMapping("userContact")
public class UserContactController extends ABaseController {
	@Resource
	private UserContactService userContactService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(UserContactQuery query){
		return getSuccessResponseVo(userContactService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(UserContact bean){
		userContactService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<UserContact> listBean){
		userContactService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<UserContact> listBean){
		userContactService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据UserIdAndContactId查询
	 */
	@RequestMapping("getUserContactByUserIdAndContactId")
	public ResponseVo getUserContactByUserIdAndContactId(String userId, String contactId){
		return getSuccessResponseVo(userContactService.getUserContactByUserIdAndContactId(userId, contactId));
	}


	/**
	 * 根据UserIdAndContactId更新
	 */
	@RequestMapping("updateUserContactByUserIdAndContactId")
	public ResponseVo updateUserContactByUserIdAndContactId(UserContact bean, String userId, String contactId){
		return getSuccessResponseVo(userContactService.updateUserContactByUserIdAndContactId(bean,userId, contactId));
	}


	/**
	 * 根据UserIdAndContactId删除
	 */
	@RequestMapping("deleteUserContactByUserIdAndContactId")
	public ResponseVo deleteUserContactByUserIdAndContactId(String userId, String contactId){
		return getSuccessResponseVo(userContactService.deleteUserContactByUserIdAndContactId(userId, contactId));
	}

}
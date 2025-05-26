package com.tockm.controller;
import com.tockm.service.UserInfoService;
import com.tockm.entity.po.UserInfo;
import com.tockm.entity.query.UserInfoQuery;
import com.tockm.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: Service
 *@date: 2025/05/26
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController extends ABaseController {
	@Resource
	private UserInfoService userInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(UserInfoQuery query){
		return getSuccessResponseVo(userInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(UserInfo bean){
		userInfoService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<UserInfo> listBean){
		userInfoService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<UserInfo> listBean){
		userInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据UserId查询
	 */
	@RequestMapping("getUserInfoByUserId")
	public ResponseVo getUserInfoByUserId(Integer userId){
		return getSuccessResponseVo(userInfoService.getUserInfoByUserId(userId));
	}


	/**
	 * 根据UserId更新
	 */
	@RequestMapping("updateUserInfoByUserId")
	public ResponseVo updateUserInfoByUserId(UserInfo bean, Integer userId){
		return getSuccessResponseVo(userInfoService.updateUserInfoByUserId(bean,userId));
	}


	/**
	 * 根据UserId删除
	 */
	@RequestMapping("deleteUserInfoByUserId")
	public ResponseVo deleteUserInfoByUserId(Integer userId){
		return getSuccessResponseVo(userInfoService.deleteUserInfoByUserId(userId));
	}

}
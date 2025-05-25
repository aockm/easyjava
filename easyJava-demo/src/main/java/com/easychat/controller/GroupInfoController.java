package com.easychat.controller;
import com.easychat.service.GroupInfoService;
import com.easychat.entity.po.GroupInfo;
import com.easychat.entity.query.GroupInfoQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: Service
 *@date: 2025/05/12
 */
@RestController
@RequestMapping("groupInfo")
public class GroupInfoController extends ABaseController {
	@Resource
	private GroupInfoService groupInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(GroupInfoQuery query){
		return getSuccessResponseVo(groupInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(GroupInfo bean){
		groupInfoService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<GroupInfo> listBean){
		groupInfoService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<GroupInfo> listBean){
		groupInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据GroupId查询
	 */
	@RequestMapping("getGroupInfoByGroupId")
	public ResponseVo getGroupInfoByGroupId(String groupId){
		return getSuccessResponseVo(groupInfoService.getGroupInfoByGroupId(groupId));
	}


	/**
	 * 根据GroupId更新
	 */
	@RequestMapping("updateGroupInfoByGroupId")
	public ResponseVo updateGroupInfoByGroupId(GroupInfo bean, String groupId){
		return getSuccessResponseVo(groupInfoService.updateGroupInfoByGroupId(bean,groupId));
	}


	/**
	 * 根据GroupId删除
	 */
	@RequestMapping("deleteGroupInfoByGroupId")
	public ResponseVo deleteGroupInfoByGroupId(String groupId){
		return getSuccessResponseVo(groupInfoService.deleteGroupInfoByGroupId(groupId));
	}

}
package com.easychat.controller;
import com.easychat.service.AppUpdateService;
import com.easychat.entity.po.AppUpdate;
import com.easychat.entity.query.AppUpdateQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: app发布Service
 *@date: 2025/05/12
 */
@RestController
@RequestMapping("appUpdate")
public class AppUpdateController extends ABaseController {
	@Resource
	private AppUpdateService appUpdateService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(AppUpdateQuery query){
		return getSuccessResponseVo(appUpdateService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(AppUpdate bean){
		appUpdateService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<AppUpdate> listBean){
		appUpdateService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<AppUpdate> listBean){
		appUpdateService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据Id查询
	 */
	@RequestMapping("getAppUpdateById")
	public ResponseVo getAppUpdateById(Integer id){
		return getSuccessResponseVo(appUpdateService.getAppUpdateById(id));
	}


	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateAppUpdateById")
	public ResponseVo updateAppUpdateById(AppUpdate bean, Integer id){
		return getSuccessResponseVo(appUpdateService.updateAppUpdateById(bean,id));
	}


	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteAppUpdateById")
	public ResponseVo deleteAppUpdateById(Integer id){
		return getSuccessResponseVo(appUpdateService.deleteAppUpdateById(id));
	}


	/**
	 * 根据Version查询
	 */
	@RequestMapping("getAppUpdateByVersion")
	public ResponseVo getAppUpdateByVersion(String version){
		return getSuccessResponseVo(appUpdateService.getAppUpdateByVersion(version));
	}


	/**
	 * 根据Version更新
	 */
	@RequestMapping("updateAppUpdateByVersion")
	public ResponseVo updateAppUpdateByVersion(AppUpdate bean, String version){
		return getSuccessResponseVo(appUpdateService.updateAppUpdateByVersion(bean,version));
	}


	/**
	 * 根据Version删除
	 */
	@RequestMapping("deleteAppUpdateByVersion")
	public ResponseVo deleteAppUpdateByVersion(String version){
		return getSuccessResponseVo(appUpdateService.deleteAppUpdateByVersion(version));
	}

}
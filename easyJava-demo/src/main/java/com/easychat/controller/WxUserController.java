package com.easychat.controller;
import com.easychat.service.WxUserService;
import com.easychat.entity.po.WxUser;
import com.easychat.entity.query.WxUserQuery;
import com.easychat.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: Service
 *@date: 2025/04/21
 */
@RestController
@RequestMapping("wxUser")
public class WxUserController extends ABaseController {
	@Resource
	private WxUserService wxUserService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(WxUserQuery query){
		return getSuccessResponseVo(wxUserService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(WxUser bean){
		wxUserService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<WxUser> listBean){
		wxUserService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<WxUser> listBean){
		wxUserService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据Id查询
	 */
	@RequestMapping("getWxUserById")
	public ResponseVo getWxUserById(Long id){
		return getSuccessResponseVo(wxUserService.getWxUserById(id));
	}


	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateWxUserById")
	public ResponseVo updateWxUserById(WxUser bean, Long id){
		return getSuccessResponseVo(wxUserService.updateWxUserById(bean,id));
	}


	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteWxUserById")
	public ResponseVo deleteWxUserById(Long id){
		return getSuccessResponseVo(wxUserService.deleteWxUserById(id));
	}

}
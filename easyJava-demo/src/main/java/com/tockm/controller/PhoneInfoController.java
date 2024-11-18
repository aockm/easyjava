package com.tockm.controller;
import com.tockm.service.PhoneInfoService;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: 手机号码Service
 *@date: 2024/11/19
 */
@RestController
public class PhoneInfoController extends ABaseController {
	@Resource
	private PhoneInfoService phoneInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(PhoneInfoQuery query){
		return getSuccessResponseVo(phoneInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	public ResponseVo add(PhoneInfo bean){
		phoneInfoService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	public ResponseVo addBatch(@RequestBody List<PhoneInfo> listBean){
		phoneInfoService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	public ResponseVo addOrUpdateBatch(@RequestBody List<PhoneInfo> listBean){
		phoneInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据Id查询
	 */
	public ResponseVo getPhoneInfoById(Integer id){
		return getSuccessResponseVo(phoneInfoService.getPhoneInfoById(id));
	}


	/**
	 * 根据Id更新
	 */
	public ResponseVo updatePhoneInfoById(PhoneInfo bean, Integer id){
		return getSuccessResponseVo(phoneInfoService.updatePhoneInfoById(bean,id));
	}


	/**
	 * 根据Id删除
	 */
	public ResponseVo deletePhoneInfoById(Integer id){
		return getSuccessResponseVo(phoneInfoService.deletePhoneInfoById(id));
	}

}
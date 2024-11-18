package com.tockm.controller;
import com.tockm.service.NameInfoService;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import com.tockm.entity.vo.ResponseVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description: Service
 *@date: 2024/11/19
 */
@RestController
public class NameInfoController extends ABaseController {
	@Resource
	private NameInfoService nameInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(NameInfoQuery query){
		return getSuccessResponseVo(nameInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	public ResponseVo add(NameInfo bean){
		nameInfoService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	public ResponseVo addBatch(@RequestBody List<NameInfo> listBean){
		nameInfoService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	public ResponseVo addOrUpdateBatch(@RequestBody List<NameInfo> listBean){
		nameInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据Id查询
	 */
	public ResponseVo getNameInfoById(Integer id){
		return getSuccessResponseVo(nameInfoService.getNameInfoById(id));
	}


	/**
	 * 根据Id更新
	 */
	public ResponseVo updateNameInfoById(NameInfo bean, Integer id){
		return getSuccessResponseVo(nameInfoService.updateNameInfoById(bean,id));
	}


	/**
	 * 根据Id删除
	 */
	public ResponseVo deleteNameInfoById(Integer id){
		return getSuccessResponseVo(nameInfoService.deleteNameInfoById(id));
	}


	/**
	 * 根据Name查询
	 */
	public ResponseVo getNameInfoByName(String name){
		return getSuccessResponseVo(nameInfoService.getNameInfoByName(name));
	}


	/**
	 * 根据Name更新
	 */
	public ResponseVo updateNameInfoByName(NameInfo bean, String name){
		return getSuccessResponseVo(nameInfoService.updateNameInfoByName(bean,name));
	}


	/**
	 * 根据Name删除
	 */
	public ResponseVo deleteNameInfoByName(String name){
		return getSuccessResponseVo(nameInfoService.deleteNameInfoByName(name));
	}

}
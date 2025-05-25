package com.easychat.controller;
import com.easychat.service.ParsingInfoService;
import com.easychat.entity.po.ParsingInfo;
import com.easychat.entity.query.ParsingInfoQuery;
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
@RequestMapping("parsingInfo")
public class ParsingInfoController extends ABaseController {
	@Resource
	private ParsingInfoService parsingInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(ParsingInfoQuery query){
		return getSuccessResponseVo(parsingInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(ParsingInfo bean){
		parsingInfoService.add(bean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<ParsingInfo> listBean){
		parsingInfoService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<ParsingInfo> listBean){
		parsingInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}


	/**
	 * 根据Id查询
	 */
	@RequestMapping("getParsingInfoById")
	public ResponseVo getParsingInfoById(Long id){
		return getSuccessResponseVo(parsingInfoService.getParsingInfoById(id));
	}


	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateParsingInfoById")
	public ResponseVo updateParsingInfoById(ParsingInfo bean, Long id){
		return getSuccessResponseVo(parsingInfoService.updateParsingInfoById(bean,id));
	}


	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteParsingInfoById")
	public ResponseVo deleteParsingInfoById(Long id){
		return getSuccessResponseVo(parsingInfoService.deleteParsingInfoById(id));
	}

}
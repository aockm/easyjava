package com.easychat.service.impl;
import com.easychat.service.ParsingInfoService;
import com.easychat.entity.po.ParsingInfo;
import com.easychat.entity.query.ParsingInfoQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.ParsingInfoMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: Service
 *@date: 2025/04/21
 */
@Service("parsingInfoService")
public class ParsingInfoServiceImpl implements ParsingInfoService {
	@Resource
	private ParsingInfoMapper<ParsingInfo,ParsingInfoQuery> parsingInfoMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ParsingInfo> findListByParam(ParsingInfoQuery param){
		return this.parsingInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(ParsingInfoQuery param){
		return this.parsingInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<ParsingInfo> findListByPage(ParsingInfoQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ParsingInfo> list = this.findListByParam(query);
		PaginationResultVo<ParsingInfo> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ParsingInfo bean){
		return this.parsingInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ParsingInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.parsingInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ParsingInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.parsingInfoMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public ParsingInfo getParsingInfoById(Long id){
		return this.parsingInfoMapper.selectById(id);
	}


	/**
	 * 根据Id更新
	 */
	@Override
	public Integer updateParsingInfoById(ParsingInfo bean, Long id){
		return this.parsingInfoMapper.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteParsingInfoById(Long id){
		return this.parsingInfoMapper.deleteById(id);
	}

}
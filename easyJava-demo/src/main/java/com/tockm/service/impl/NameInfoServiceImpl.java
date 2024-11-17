package com.tockm.service.impl;
import com.tockm.service.NameInfoService;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import com.tockm.entity.query.SimplePage;
import com.tockm.mappers.NameInfoMapper;
import com.tockm.enums.PageSize;
import com.tockm.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: Service
 *@date: 2024/11/17
 */
@Service("nameInfoService")
public class NameInfoServiceImpl implements NameInfoService {
	@Resource
	private NameInfoMapper<NameInfo,NameInfoQuery> nameInfoMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<NameInfo> findListByParam(NameInfoQuery param){
		return this.nameInfoMapper.selectList(param);
	}

	@Override
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(NameInfoQuery param){
		return this.nameInfoMapper.selectCount(param);
	}

	@Override
	/**
	 * 分页查询
	 */
	public PaginationResultVo<NameInfo> findListByPage(NameInfoQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<NameInfo> list = this.findListByParam(query);
		PaginationResultVo<NameInfo> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	@Override
	/**
	 * 新增
	 */
	public Integer add(NameInfo bean){
	}

	@Override
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<NameInfo> listBean){
	}

	@Override
	/**
	 * 批量新增/修改
	 */
	public Integer addOrUpdateBatch(NameInfo bean){
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public NameInfo getById(Integer id){
	}


	/**
	 * 根据Id更新
	 */
	public Integer updateById(NameInfo bean, Integer id){
	}


	/**
	 * 根据Id删除
	 */
	public Integer deleteById(Integer id){
	}


	/**
	 * 根据Name查询
	 */
	@Override
	public NameInfo getByName(String name){
	}


	/**
	 * 根据Name更新
	 */
	public Integer updateByName(NameInfo bean, String name){
	}


	/**
	 * 根据Name删除
	 */
	public Integer deleteByName(String name){
	}

}
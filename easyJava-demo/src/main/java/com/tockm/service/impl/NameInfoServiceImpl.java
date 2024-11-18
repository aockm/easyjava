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
 *@date: 2024/11/19
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

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(NameInfoQuery param){
		return this.nameInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<NameInfo> findListByPage(NameInfoQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<NameInfo> list = this.findListByParam(query);
		PaginationResultVo<NameInfo> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(NameInfo bean){
		return this.nameInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<NameInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.nameInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<NameInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.nameInfoMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public NameInfo getNameInfoById(Integer id){
		return this.nameInfoMapper.selectById(id);
	}


	/**
	 * 根据Id更新
	 */
	@Override
	public Integer updateNameInfoById(NameInfo bean, Integer id){
		return this.nameInfoMapper.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteNameInfoById(Integer id){
		return this.nameInfoMapper.deleteById(id);
	}


	/**
	 * 根据Name查询
	 */
	@Override
	public NameInfo getNameInfoByName(String name){
		return this.nameInfoMapper.selectByName(name);
	}


	/**
	 * 根据Name更新
	 */
	@Override
	public Integer updateNameInfoByName(NameInfo bean, String name){
		return this.nameInfoMapper.updateByName(bean,name);
	}


	/**
	 * 根据Name删除
	 */
	@Override
	public Integer deleteNameInfoByName(String name){
		return this.nameInfoMapper.deleteByName(name);
	}

}
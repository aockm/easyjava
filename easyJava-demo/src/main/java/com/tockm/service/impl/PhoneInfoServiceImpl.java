package com.tockm.service.impl;
import com.tockm.service.PhoneInfoService;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.entity.query.SimplePage;
import com.tockm.mappers.PhoneInfoMapper;
import com.tockm.enums.PageSize;
import com.tockm.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 手机号码Service
 *@date: 2024/11/17
 */
@Service("phoneInfoService")
public class PhoneInfoServiceImpl implements PhoneInfoService {
	@Resource
	private PhoneInfoMapper<PhoneInfo,PhoneInfoQuery> phoneInfoMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<PhoneInfo> findListByParam(PhoneInfoQuery param){
		return this.phoneInfoMapper.selectList(param);
	}

	@Override
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(PhoneInfoQuery param){
		return this.phoneInfoMapper.selectCount(param);
	}

	@Override
	/**
	 * 分页查询
	 */
	public PaginationResultVo<PhoneInfo> findListByPage(PhoneInfoQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<PhoneInfo> list = this.findListByParam(query);
		PaginationResultVo<PhoneInfo> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	@Override
	/**
	 * 新增
	 */
	public Integer add(PhoneInfo bean){
	}

	@Override
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<PhoneInfo> listBean){
	}

	@Override
	/**
	 * 批量新增/修改
	 */
	public Integer addOrUpdateBatch(PhoneInfo bean){
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public PhoneInfo getById(Integer id){
	}


	/**
	 * 根据Id更新
	 */
	public Integer updateById(PhoneInfo bean, Integer id){
	}


	/**
	 * 根据Id删除
	 */
	public Integer deleteById(Integer id){
	}

}
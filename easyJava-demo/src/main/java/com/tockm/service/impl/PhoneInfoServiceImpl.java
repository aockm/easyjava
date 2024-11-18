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
 *@date: 2024/11/19
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

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(PhoneInfoQuery param){
		return this.phoneInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<PhoneInfo> findListByPage(PhoneInfoQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<PhoneInfo> list = this.findListByParam(query);
		PaginationResultVo<PhoneInfo> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(PhoneInfo bean){
		return this.phoneInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<PhoneInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.phoneInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<PhoneInfo> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.phoneInfoMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public PhoneInfo getPhoneInfoById(Integer id){
		return this.phoneInfoMapper.selectById(id);
	}


	/**
	 * 根据Id更新
	 */
	@Override
	public Integer updatePhoneInfoById(PhoneInfo bean, Integer id){
		return this.phoneInfoMapper.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deletePhoneInfoById(Integer id){
		return this.phoneInfoMapper.deleteById(id);
	}

}
package com.easychat.service.impl;
import com.easychat.service.WxUserService;
import com.easychat.entity.po.WxUser;
import com.easychat.entity.query.WxUserQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.WxUserMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: Service
 *@date: 2025/04/21
 */
@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {
	@Resource
	private WxUserMapper<WxUser,WxUserQuery> wxUserMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<WxUser> findListByParam(WxUserQuery param){
		return this.wxUserMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(WxUserQuery param){
		return this.wxUserMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<WxUser> findListByPage(WxUserQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<WxUser> list = this.findListByParam(query);
		PaginationResultVo<WxUser> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(WxUser bean){
		return this.wxUserMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<WxUser> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.wxUserMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<WxUser> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.wxUserMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public WxUser getWxUserById(Long id){
		return this.wxUserMapper.selectById(id);
	}


	/**
	 * 根据Id更新
	 */
	@Override
	public Integer updateWxUserById(WxUser bean, Long id){
		return this.wxUserMapper.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteWxUserById(Long id){
		return this.wxUserMapper.deleteById(id);
	}

}
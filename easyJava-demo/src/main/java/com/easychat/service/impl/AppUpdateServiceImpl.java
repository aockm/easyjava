package com.easychat.service.impl;
import com.easychat.service.AppUpdateService;
import com.easychat.entity.po.AppUpdate;
import com.easychat.entity.query.AppUpdateQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.AppUpdateMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: app发布Service
 *@date: 2025/05/12
 */
@Service("appUpdateService")
public class AppUpdateServiceImpl implements AppUpdateService {
	@Resource
	private AppUpdateMapper<AppUpdate,AppUpdateQuery> appUpdateMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<AppUpdate> findListByParam(AppUpdateQuery param){
		return this.appUpdateMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(AppUpdateQuery param){
		return this.appUpdateMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<AppUpdate> findListByPage(AppUpdateQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<AppUpdate> list = this.findListByParam(query);
		PaginationResultVo<AppUpdate> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(AppUpdate bean){
		return this.appUpdateMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<AppUpdate> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.appUpdateMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<AppUpdate> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.appUpdateMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据Id查询
	 */
	@Override
	public AppUpdate getAppUpdateById(Integer id){
		return this.appUpdateMapper.selectById(id);
	}


	/**
	 * 根据Id更新
	 */
	@Override
	public Integer updateAppUpdateById(AppUpdate bean, Integer id){
		return this.appUpdateMapper.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteAppUpdateById(Integer id){
		return this.appUpdateMapper.deleteById(id);
	}


	/**
	 * 根据Version查询
	 */
	@Override
	public AppUpdate getAppUpdateByVersion(String version){
		return this.appUpdateMapper.selectByVersion(version);
	}


	/**
	 * 根据Version更新
	 */
	@Override
	public Integer updateAppUpdateByVersion(AppUpdate bean, String version){
		return this.appUpdateMapper.updateByVersion(bean,version);
	}


	/**
	 * 根据Version删除
	 */
	@Override
	public Integer deleteAppUpdateByVersion(String version){
		return this.appUpdateMapper.deleteByVersion(version);
	}

}
package com.easychat.service.impl;
import com.easychat.service.UserContactService;
import com.easychat.entity.po.UserContact;
import com.easychat.entity.query.UserContactQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.UserContactMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 联系人Service
 *@date: 2025/05/12
 */
@Service("userContactService")
public class UserContactServiceImpl implements UserContactService {
	@Resource
	private UserContactMapper<UserContact,UserContactQuery> userContactMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserContact> findListByParam(UserContactQuery param){
		return this.userContactMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(UserContactQuery param){
		return this.userContactMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<UserContact> findListByPage(UserContactQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<UserContact> list = this.findListByParam(query);
		PaginationResultVo<UserContact> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserContact bean){
		return this.userContactMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserContact> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.userContactMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserContact> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.userContactMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据UserIdAndContactId查询
	 */
	@Override
	public UserContact getUserContactByUserIdAndContactId(String userId, String contactId){
		return this.userContactMapper.selectByUserIdAndContactId(userId, contactId);
	}


	/**
	 * 根据UserIdAndContactId更新
	 */
	@Override
	public Integer updateUserContactByUserIdAndContactId(UserContact bean, String userId, String contactId){
		return this.userContactMapper.updateByUserIdAndContactId(bean,userId, contactId);
	}


	/**
	 * 根据UserIdAndContactId删除
	 */
	@Override
	public Integer deleteUserContactByUserIdAndContactId(String userId, String contactId){
		return this.userContactMapper.deleteByUserIdAndContactId(userId, contactId);
	}

}
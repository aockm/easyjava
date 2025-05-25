package com.easychat.service.impl;
import com.easychat.service.UserContactApplyService;
import com.easychat.entity.po.UserContactApply;
import com.easychat.entity.query.UserContactApplyQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.UserContactApplyMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 联系人申请Service
 *@date: 2025/05/12
 */
@Service("userContactApplyService")
public class UserContactApplyServiceImpl implements UserContactApplyService {
	@Resource
	private UserContactApplyMapper<UserContactApply,UserContactApplyQuery> userContactApplyMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserContactApply> findListByParam(UserContactApplyQuery param){
		return this.userContactApplyMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(UserContactApplyQuery param){
		return this.userContactApplyMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<UserContactApply> findListByPage(UserContactApplyQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<UserContactApply> list = this.findListByParam(query);
		PaginationResultVo<UserContactApply> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserContactApply bean){
		return this.userContactApplyMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserContactApply> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.userContactApplyMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserContactApply> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.userContactApplyMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据ApplyId查询
	 */
	@Override
	public UserContactApply getUserContactApplyByApplyId(Integer applyId){
		return this.userContactApplyMapper.selectByApplyId(applyId);
	}


	/**
	 * 根据ApplyId更新
	 */
	@Override
	public Integer updateUserContactApplyByApplyId(UserContactApply bean, Integer applyId){
		return this.userContactApplyMapper.updateByApplyId(bean,applyId);
	}


	/**
	 * 根据ApplyId删除
	 */
	@Override
	public Integer deleteUserContactApplyByApplyId(Integer applyId){
		return this.userContactApplyMapper.deleteByApplyId(applyId);
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId查询
	 */
	@Override
	public UserContactApply getUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId){
		return this.userContactApplyMapper.selectByApplyUserIdAndReceiveUserIdAndContactId(applyUserId, receiveUserId, contactId);
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId更新
	 */
	@Override
	public Integer updateUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(UserContactApply bean, String applyUserId, String receiveUserId, String contactId){
		return this.userContactApplyMapper.updateByApplyUserIdAndReceiveUserIdAndContactId(bean,applyUserId, receiveUserId, contactId);
	}


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId删除
	 */
	@Override
	public Integer deleteUserContactApplyByApplyUserIdAndReceiveUserIdAndContactId(String applyUserId, String receiveUserId, String contactId){
		return this.userContactApplyMapper.deleteByApplyUserIdAndReceiveUserIdAndContactId(applyUserId, receiveUserId, contactId);
	}

}
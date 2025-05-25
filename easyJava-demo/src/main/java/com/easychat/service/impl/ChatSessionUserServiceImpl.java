package com.easychat.service.impl;
import com.easychat.service.ChatSessionUserService;
import com.easychat.entity.po.ChatSessionUser;
import com.easychat.entity.query.ChatSessionUserQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.ChatSessionUserMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 会话用户Service
 *@date: 2025/05/12
 */
@Service("chatSessionUserService")
public class ChatSessionUserServiceImpl implements ChatSessionUserService {
	@Resource
	private ChatSessionUserMapper<ChatSessionUser,ChatSessionUserQuery> chatSessionUserMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ChatSessionUser> findListByParam(ChatSessionUserQuery param){
		return this.chatSessionUserMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(ChatSessionUserQuery param){
		return this.chatSessionUserMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<ChatSessionUser> findListByPage(ChatSessionUserQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ChatSessionUser> list = this.findListByParam(query);
		PaginationResultVo<ChatSessionUser> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ChatSessionUser bean){
		return this.chatSessionUserMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ChatSessionUser> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatSessionUserMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ChatSessionUser> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatSessionUserMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据UserIdAndContactId查询
	 */
	@Override
	public ChatSessionUser getChatSessionUserByUserIdAndContactId(String userId, String contactId){
		return this.chatSessionUserMapper.selectByUserIdAndContactId(userId, contactId);
	}


	/**
	 * 根据UserIdAndContactId更新
	 */
	@Override
	public Integer updateChatSessionUserByUserIdAndContactId(ChatSessionUser bean, String userId, String contactId){
		return this.chatSessionUserMapper.updateByUserIdAndContactId(bean,userId, contactId);
	}


	/**
	 * 根据UserIdAndContactId删除
	 */
	@Override
	public Integer deleteChatSessionUserByUserIdAndContactId(String userId, String contactId){
		return this.chatSessionUserMapper.deleteByUserIdAndContactId(userId, contactId);
	}

}
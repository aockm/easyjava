package com.easychat.service.impl;
import com.easychat.service.ChatSessionService;
import com.easychat.entity.po.ChatSession;
import com.easychat.entity.query.ChatSessionQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.ChatSessionMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 联系人Service
 *@date: 2025/05/12
 */
@Service("chatSessionService")
public class ChatSessionServiceImpl implements ChatSessionService {
	@Resource
	private ChatSessionMapper<ChatSession,ChatSessionQuery> chatSessionMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ChatSession> findListByParam(ChatSessionQuery param){
		return this.chatSessionMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(ChatSessionQuery param){
		return this.chatSessionMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<ChatSession> findListByPage(ChatSessionQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ChatSession> list = this.findListByParam(query);
		PaginationResultVo<ChatSession> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ChatSession bean){
		return this.chatSessionMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ChatSession> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatSessionMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ChatSession> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatSessionMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据SessionId查询
	 */
	@Override
	public ChatSession getChatSessionBySessionId(String sessionId){
		return this.chatSessionMapper.selectBySessionId(sessionId);
	}


	/**
	 * 根据SessionId更新
	 */
	@Override
	public Integer updateChatSessionBySessionId(ChatSession bean, String sessionId){
		return this.chatSessionMapper.updateBySessionId(bean,sessionId);
	}


	/**
	 * 根据SessionId删除
	 */
	@Override
	public Integer deleteChatSessionBySessionId(String sessionId){
		return this.chatSessionMapper.deleteBySessionId(sessionId);
	}

}
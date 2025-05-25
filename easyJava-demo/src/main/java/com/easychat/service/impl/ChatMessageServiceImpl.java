package com.easychat.service.impl;
import com.easychat.service.ChatMessageService;
import com.easychat.entity.po.ChatMessage;
import com.easychat.entity.query.ChatMessageQuery;
import com.easychat.entity.query.SimplePage;
import com.easychat.mappers.ChatMessageMapper;
import com.easychat.enums.PageSize;
import com.easychat.entity.vo.PaginationResultVo;
import java.util.List;
import javax.annotation.Resource;;
import org.springframework.stereotype.Service;

/**
 *@Description: 聊天消息表Service
 *@date: 2025/05/12
 */
@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {
	@Resource
	private ChatMessageMapper<ChatMessage,ChatMessageQuery> chatMessageMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ChatMessage> findListByParam(ChatMessageQuery param){
		return this.chatMessageMapper.selectList(param);
	}

	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(ChatMessageQuery param){
		return this.chatMessageMapper.selectCount(param);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVo<ChatMessage> findListByPage(ChatMessageQuery query){
		int count = this.findCountByParam(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ChatMessage> list = this.findListByParam(query);
		PaginationResultVo<ChatMessage> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ChatMessage bean){
		return this.chatMessageMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ChatMessage> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatMessageMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ChatMessage> listBean){
		if(listBean==null||listBean.isEmpty()){
			return 0;
		}
		return this.chatMessageMapper.insertOrUpdateBatch(listBean);
	}


	/**
	 * 根据MessageId查询
	 */
	@Override
	public ChatMessage getChatMessageByMessageId(Long messageId){
		return this.chatMessageMapper.selectByMessageId(messageId);
	}


	/**
	 * 根据MessageId更新
	 */
	@Override
	public Integer updateChatMessageByMessageId(ChatMessage bean, Long messageId){
		return this.chatMessageMapper.updateByMessageId(bean,messageId);
	}


	/**
	 * 根据MessageId删除
	 */
	@Override
	public Integer deleteChatMessageByMessageId(Long messageId){
		return this.chatMessageMapper.deleteByMessageId(messageId);
	}

}
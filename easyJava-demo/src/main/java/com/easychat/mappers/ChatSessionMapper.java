package com.easychat.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *@Description: 联系人Mapper
 *@date: 2025/05/12
 */
public interface ChatSessionMapper<T,P> extends BaseMapper {

	/**
	 * 根据SessionId查询
	 */
	T selectBySessionId(@Param("sessionId") String sessionId);

	/**
	 * 根据SessionId更新
	 */
	Integer updateBySessionId(@Param("bean") T t, @Param("sessionId") String sessionId);

	/**
	 * 根据SessionId删除
	 */
	Integer deleteBySessionId(@Param("sessionId") String sessionId);

}
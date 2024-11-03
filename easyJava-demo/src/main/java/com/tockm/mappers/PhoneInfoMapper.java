package com.tockm.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *@Description: 手机号码Mapper
 *@date: 2024/11/04
 */
public interface PhoneInfoMapper<T,P> extends BaseMapper {

	/**
	 * 根据IdAndId查询
	 */
	T selectByIdAndId(@Param("id") Integer id, @Param("id") Integer id);

	/**
	 * 根据IdAndId更新
	 */
	Integer updateByIdAndId();

	/**
	 * 根据IdAndId删除
	 */
	Integer deleteByIdAndId();

}
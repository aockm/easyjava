package com.tockm.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *@Description: Mapper
 *@date: 2024/11/19
 */
public interface NameInfoMapper<T,P> extends BaseMapper {

	/**
	 * 根据Id查询
	 */
	T selectById(@Param("id") Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteById(@Param("id") Integer id);

	/**
	 * 根据Name查询
	 */
	T selectByName(@Param("name") String name);

	/**
	 * 根据Name更新
	 */
	Integer updateByName(@Param("bean") T t, @Param("name") String name);

	/**
	 * 根据Name删除
	 */
	Integer deleteByName(@Param("name") String name);

}
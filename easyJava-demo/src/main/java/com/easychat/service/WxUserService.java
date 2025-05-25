package com.easychat.service;
import com.easychat.entity.po.WxUser;
import com.easychat.entity.query.WxUserQuery;
import java.util.List;
import com.easychat.entity.vo.PaginationResultVo;

/**
 *@Description: Service
 *@date: 2025/04/21
 */
public interface WxUserService {
	/**
	 * 根据条件查询列表
	 */
	List<WxUser> findListByParam(WxUserQuery param);
	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(WxUserQuery param);
	/**
	 * 分页查询
	 */
	PaginationResultVo<WxUser> findListByPage(WxUserQuery param);
	/**
	 * 新增
	 */
	Integer add(WxUser bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<WxUser> listBean);
	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<WxUser> bean);

	/**
	 * 根据Id查询
	 */
	WxUser getWxUserById(Long id);

	/**
	 * 根据Id更新
	 */
	Integer updateWxUserById(WxUser bean, Long id);

	/**
	 * 根据Id删除
	 */
	Integer deleteWxUserById(Long id);
}
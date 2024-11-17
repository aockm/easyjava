package com.tockm.service.impl;
import com.tockm.service.PhoneInfoService;
import com.tockm.entity.po.PhoneInfo;
import com.tockm.entity.query.PhoneInfoQuery;
import com.tockm.entity.vo.PaginationResultVo;
import java.util.List;
import org.springframework.stereotype.Service;;

/**
 *@Description: 手机号码Service
 *@date: 2024/11/17
 */
@Service("phoneInfoService")
public class PhoneInfoServiceImpl implements PhoneInfoService {
	/**
	 * 根据条件查询列表
	 */
	public List<PhoneInfo> findListByParam(PhoneInfoQuery param){
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(PhoneInfoQuery param){
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVo<PhoneInfo> findListByPage(PhoneInfoQuery param){
	}
	/**
	 * 新增
	 */
	public Integer add(PhoneInfo bean){
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<PhoneInfo> listBean){
	}
	/**
	 * 批量新增/修改
	 */
	public Integer addOrUpdateBatch(PhoneInfo bean){
	}

	/**
	 * 根据Id查询
	 */
	public PhoneInfo getById(Integer id){
	}

	/**
	 * 根据Id更新
	 */
	public Integer updateById(PhoneInfo bean, Integer id){
	}

	/**
	 * 根据Id删除
	 */
	public Integer deleteById(Integer id){
	}
}
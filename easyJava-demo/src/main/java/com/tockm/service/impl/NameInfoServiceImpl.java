package com.tockm.service.impl;
import com.tockm.service.NameInfoService;
import com.tockm.entity.po.NameInfo;
import com.tockm.entity.query.NameInfoQuery;
import com.tockm.entity.vo.PaginationResultVo;
import java.util.List;
import org.springframework.stereotype.Service;;

/**
 *@Description: Service
 *@date: 2024/11/17
 */
@Service("nameInfoService")
public class NameInfoServiceImpl implements NameInfoService {
	/**
	 * 根据条件查询列表
	 */
	public List<NameInfo> findListByParam(NameInfoQuery param){
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(NameInfoQuery param){
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVo<NameInfo> findListByPage(NameInfoQuery param){
	}
	/**
	 * 新增
	 */
	public Integer add(NameInfo bean){
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<NameInfo> listBean){
	}
	/**
	 * 批量新增/修改
	 */
	public Integer addOrUpdateBatch(NameInfo bean){
	}

	/**
	 * 根据Id查询
	 */
	public NameInfo getById(Integer id){
	}

	/**
	 * 根据Id更新
	 */
	public Integer updateById(NameInfo bean, Integer id){
	}

	/**
	 * 根据Id删除
	 */
	public Integer deleteById(Integer id){
	}

	/**
	 * 根据Name查询
	 */
	public NameInfo getByName(String name){
	}

	/**
	 * 根据Name更新
	 */
	public Integer updateByName(NameInfo bean, String name){
	}

	/**
	 * 根据Name删除
	 */
	public Integer deleteByName(String name){
	}
}
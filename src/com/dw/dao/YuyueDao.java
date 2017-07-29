package com.dw.dao;

import java.util.List;


import com.dw.model.Yuyue;

public interface YuyueDao {
	/**
	 * 获取指定的预约单(更新页面操作使用)
	 * 
	 * @param id
	 * @return yuyue
	 */
	public  Yuyue findYuyueByid(int id);
		

	/**
	 * 添加预约单信息
	 * 
	 * @param yuyue
	 * @return flag
	 */
	public boolean addYuyue(Yuyue yuyue);

	/**
	 * 删除预约单信息
	 * 
	 * @param id
	 * @return flag
	 */
	public boolean delYuyue(int id);

	/**
	 * 更新预约单信息
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean updateYuyue(Yuyue yuyue);

	/**
	 * 查询全体预约单信息
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List YuSelect();
	/**
	 * 根据ID查询预约单信息
	 * 
	 * @return Yuyue
	 */
	 public Yuyue findYuyueById(String id); 
}

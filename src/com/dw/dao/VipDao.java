package com.dw.dao;

/**
 * 数据访问层，学生信息CRUD操作
 * @author DY1101shaoyuxian
 */

import java.util.List;


import com.dw.model.Vip;


public interface VipDao {
	
	/**
	 * 获取指定的学生(更新页面操作使用)
	 * 
	 * @param id
	 * @return yuyue
	 */
	public Vip findVipByOpenid(String id);
		
        /**
         * 根据用户昵称返回指定用户
         * @param nickname
         * @return
         */
	public List findVipByNickname(String nickname);
	/**
	 * 添加学生信息
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean addVip(Vip vip);

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 * @return flag
	 *//*
	public boolean delVip(String id);

	*//**
	 * 更新学生信息
	 * 
	 * @param stu
	 * @return flag
	 *//*
	public boolean updateVip(Vip vip);
*/
	/**
	 * 查询全体学生信息
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List vipSelect();
	/**
	 * 根据ID查询学生信息
	 * 
	 * @return Student
	 *//*
	 public Vip findVipById(String id); */

}

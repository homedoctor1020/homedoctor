package com.dw.dao;


import java.sql.SQLException;

import com.dw.model.User;
/**
 *  数据库访问层-管理员的CRUD操作
 * @author DY1101shaoyuxian
 *
 */
public interface UserDao {
	    /**
	     * 登陆用户验证
	     * @param page
	     * @return
	     * @throws SQLException 
	     */
	  public boolean isLogin(User user);

		 /**
		  * 修改用户的密码
		  * @param page
		  * @return
		 * @throws SQLException 
		 *  
		  */
		
		public int updateUserPassWord(User  user) ;
}

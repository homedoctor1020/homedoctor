package com.dw.dao;


import java.sql.SQLException;

import com.dw.model.User;
/**
 *  ���ݿ���ʲ�-����Ա��CRUD����
 * @author DY1101shaoyuxian
 *
 */
public interface UserDao {
	    /**
	     * ��½�û���֤
	     * @param page
	     * @return
	     * @throws SQLException 
	     */
	  public boolean isLogin(User user);

		 /**
		  * �޸��û�������
		  * @param page
		  * @return
		 * @throws SQLException 
		 *  
		  */
		
		public int updateUserPassWord(User  user) ;
}

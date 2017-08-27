package com.dw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dw.dao.UserDao;
import com.dw.model.User;
import com.dw.util.DbConn;
/**
 *  ���ݿ���ʲ�-����Ա��CRUD����
 * @author DY1101shaoyuxian
 *
 */
public class UserDaoImpl implements UserDao {
	private Connection conn = DbConn.getConn();
	//��½�û���֤
	  public boolean isLogin(User user) {
		  boolean flag=false;
		  PreparedStatement pmst=null;
		  String sql="select * from t_manager where username=? and password=?";
		  try {
			pmst=conn.prepareStatement(sql);
			pmst.setString(1, user.getUsername());//�Դ��������û�����������з�װ
			pmst.setString(2, user.getPassword());
			ResultSet rs=pmst.executeQuery();
			if(rs.next()){
				flag=true;				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		    {
		      if(pmst!= null)
			try {
			    pmst.close();
			} catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} 		
		      if(conn!= null)
			try {
			    conn.close();
			} catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} 
		    }
		return flag;	  
	  }

		 /**
		  * �޸��û�������
		  * @param page
		  * @return
		 * @throws SQLException 
		  */
		
		public int updateUserPassWord(User  user)  {
			int a=0;
			String sql = "update t_manager set password=? where username=?";
			PreparedStatement pstmt=null;
			try {
				 pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getUsername());
				a= pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			    {
			      if(pstmt!= null)
				try {
				    pstmt.close();
				} catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				} 		
			      if(conn!= null)
				try {
				    conn.close();
				} catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				} 
			    }
			return a;
		}
}

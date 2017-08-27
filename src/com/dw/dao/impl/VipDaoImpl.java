package com.dw.dao.impl;

/**
 * 数据访问层，学生信息CRUD操作
 * @author DY1101shaoyuxian
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.dw.dao.VipDao;
import com.dw.model.Vip;
import com.dw.util.DbConn;

public class VipDaoImpl implements VipDao {
	private Connection conn = DbConn.getConn();

	/**
	 * 获取指定的用戶(更新页面操作使用)
	 * 
	 * @param id
	 * @return stu
	 */
	public Vip findVipByOpenid(String id) {
		Vip vip = null;
		String sql = "select * from vip where openId=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String openid = rs.getString("openID");
				String nickname = rs.getString("nickname");
				int sex = rs.getInt("sex");
				String city = rs.getString("city");
				String headimgurl = rs.getString("headimgurl");
				vip = new Vip(openid,nickname, sex, city,headimgurl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vip;
	}
	/**
	 * 返回指定昵称的用户列表
	 */
	public List findVipByNickname(String nickname){
	    Vip vip = null;
	    List list=new ArrayList();
		String sql = "select * from vip where nickname like concat('%',?,'%')";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String openid = rs.getString("openID");
				int sex = rs.getInt("sex");
				String name = rs.getString("nickname");
				String city = rs.getString("city");
				String headimgurl = rs.getString("headimgurl");
				vip = new Vip(openid,name, sex, city,headimgurl);
				list.add(vip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 添加学生信息
	 * 
	 * @param stu
	 * @retursn flag
	 */
	public boolean addVip(Vip vip) {
		// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
		boolean flag = false;
		PreparedStatement psmt=null;
		String sql = "insert  IGNORE into vip(openID,nickname,subscribe,subscribeTime,sex,country,province,city,headimgurl) values(?,?,?,?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vip.getOpenId());
			psmt.setString(2, vip.getNickname());
			psmt.setInt(3, vip.getSubscribe());
			psmt.setString(4, vip.getSubscribeTime());
			psmt.setInt(5, vip.getSex());
			psmt.setString(6, vip.getCountry());
			psmt.setString(7, vip.getProvince());
			psmt.setString(8, vip.getCity());
			//System.out.println(vip.getNickname()+"的img="+vip.getHeadImgUrl());
			psmt.setString(9, vip.getHeadImgUrl());
			int i = psmt.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		    {
		      if(psmt!= null)
			try {
			    psmt.close();
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
	
	@Override
	public String getNicknameByopenid(String openid) {
	// TODO Auto-generated method stub
	    String sql="select nickname from vip where openID=?";
	    String nickname=null;
	    PreparedStatement psmt =null;
	    try {
	    	psmt = conn.prepareStatement(sql);
	    	psmt.setString(1, openid);
	    	
	    	ResultSet rs = psmt.executeQuery();
	    	while (rs.next()) {
	    		 nickname = rs.getString("nickname");
	    	}
	    } catch (SQLException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }finally
	    {
		      if(psmt!= null)
			try {
			    psmt.close();
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
	    return nickname;
	}
@Override
public String getImageByopenid(String openid)  {
// TODO Auto-generated method stub
String sql="select headimgurl from vip where openID=?";
String image=null;
PreparedStatement psmt =null;
try {
	psmt = conn.prepareStatement(sql);
	psmt.setString(1, openid);
	
	ResultSet rs = psmt.executeQuery();
	while (rs.next()) {
		 image = rs.getString("headimgurl");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally
{
    if(psmt!= null)
	try {
	    psmt.close();
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
return image;
}
	/**
	 * 查询全体学生信息
	 * 
	 * @return list
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List vipSelect()  {
		List list = new ArrayList();
		String sql = "select * from vip";
		Statement smt=null;
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
			while (rs.next()) {
			    String openid = rs.getString("openID");
				String nickname = rs.getString("nickname");
				int sex = rs.getInt("sex");
				String city = rs.getString("city");
				String headimgurl = rs.getString("headimgurl");
				 Vip vip = new Vip(openid,nickname, sex, city,headimgurl);
				list.add(vip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		    {
		      if(smt!= null)
			try {
			    smt.close();
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
		return list;
	}
	
}

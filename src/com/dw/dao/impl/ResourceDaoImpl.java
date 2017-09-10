package com.dw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dw.dao.ResourceDao;
import com.dw.model.Resource;
import com.dw.model.Vip;
import com.dw.util.DbConn;
import java.util.Date; 

public class ResourceDaoImpl implements ResourceDao {
    private Connection conn = DbConn.getConn();
    
    @Override
    public boolean addResource(Resource re) {
	// TODO Auto-generated method stub
	String sql="insert into resource (openId,newfilename,originName,size,type) values(?,?,?,?,?)";
	PreparedStatement psmt=null;
	boolean flag=false;
	try {
		 psmt = conn.prepareStatement(sql);
		psmt.setString(1, re.getOpenid());
		psmt.setString(2, re.getNewfileName());
		psmt.setString(3, re.getOriginName());
		psmt.setString(4, re.getSize());
		psmt.setInt(5,re.getType());
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
    public List selectAll() {
	// TODO Auto-generated method stub
	Resource re=null;
	List list =new ArrayList();
	PreparedStatement psmt=null;
	String sql="select * from resource GROUP BY openId,resourceId ORDER BY createTime";
	try {
		 psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
		    Timestamp timestamp=  rs.getTimestamp("createTime");
		  /*  if (timestamp != null){
			Date date = new java.util.Date(timestamp.getTime()));
		    }*/
		    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
		        String createtime = dFormat.format(timestamp);  
			re=new Resource(rs.getString("openid"),rs.getString("originName"),rs.getString("newfilename"),rs.getString("size"),rs.getInt("type"),createtime);
			list.add(re);
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
	return list;
    }
    @Override
    public List findByOpenid(String openid){
	// TODO Auto-generated method stub
	Resource re=null;
	List list =new ArrayList();
	PreparedStatement psmt=null;
	String sql="select * from resource where openId=?";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, openid);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
		    Timestamp timestamp=  rs.getTimestamp("createTime");
		  /*  if (timestamp != null){
			Date date = new java.util.Date(timestamp.getTime()));
		    }*/
		    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
		        String createtime = dFormat.format(timestamp);  
			re=new Resource(rs.getString("openid"),rs.getString("originName"),rs.getString("newfilename"),rs.getString("size"),rs.getInt("type"),createtime);
			list.add(re);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	    {
	      if(psmt!= null)
		try {
		    psmt.close();
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
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
    @Override
    public boolean delete(String openid, String filename) {
        // TODO Auto-generated method stub
	String sql="delete from resource where openId=? and newfilename=?";
	PreparedStatement psmt=null;
	boolean flag=false;
	try {
		 psmt = conn.prepareStatement(sql);
		psmt.setString(1, openid);
		psmt.setString(2, filename);
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
    public String findfileByOpenid(String openid, String filetype) {
	String filename=null;
	PreparedStatement psmt=null;
	String sql="select newfilename from resource where openId=? and type=? ORDER BY createTime desc limit 1 ";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, openid);
		psmt.setInt(2, Integer.parseInt(filetype));
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
		    filename=rs.getString("newfilename");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	    {
	      if(psmt!= null)
		try {
		    psmt.close();
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		} 		
	      if(conn!= null)
		try {
		    conn.close();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
	    }
	return filename;
    }

}

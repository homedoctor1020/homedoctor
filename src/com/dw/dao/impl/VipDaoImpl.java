package com.dw.dao.impl;

/**
 * ���ݷ��ʲ㣬ѧ����ϢCRUD����
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
	 * ��ȡָ�����Ñ�(����ҳ�����ʹ��)
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
	 * ����ָ���ǳƵ��û��б�
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
	 * ���ѧ����Ϣ
	 * 
	 * @param stu
	 * @retursn flag
	 */
	public boolean addVip(Vip vip) {
		// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
		boolean flag = false;
		String sql = "insert  IGNORE into vip(openID,nickname,subscribe,subscribeTime,sex,country,province,city,headimgurl) values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vip.getOpenId());
			psmt.setString(2, vip.getNickname());
			psmt.setInt(3, vip.getSubscribe());
			psmt.setString(4, vip.getSubscribeTime());
			psmt.setInt(5, vip.getSex());
			psmt.setString(6, vip.getCountry());
			psmt.setString(7, vip.getProvince());
			psmt.setString(8, vip.getCity());
			//System.out.println(vip.getNickname()+"��img="+vip.getHeadImgUrl());
			psmt.setString(9, vip.getHeadImgUrl());
			int i = psmt.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ɾ��ѧ����Ϣ
	 * 
	 * @param id
	 * @return flag
	 *//*
	public boolean delStudent(int id) {
		boolean flag = false;
		String sql = "delete from Student2 where stId=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			if (psmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	*//**
	 * ����ѧ����Ϣ
	 * 
	 * @param stu
	 * @return flag
	 *//*
	public boolean updateStudent(Student stu) {
		boolean flag = false;
		String sql = "update Student2 set stName=?,stSex=?,stAge=?,stTel=?,stDept=?,stAddress=? where stId=? ";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, stu.getStName());
			psmt.setString(2, stu.getStSex());
			psmt.setString(3, stu.getStAge());
			psmt.setLong(4, stu.getStTel());
			psmt.setString(5, stu.getStDept());
			psmt.setString(6, stu.getStAddress());
			psmt.setInt(7, stu.getStId());

			int i = psmt.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
*/
	/**
	 * ��ѯȫ��ѧ����Ϣ
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List vipSelect() {
		List list = new ArrayList();
		String sql = "select * from vip";
		try {
			Statement smt = conn.createStatement();
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
		}
		return list;
	}

	

	/*@Override
	public Vip findVipById(String id) {
		// TODO Auto-generated method stub
		Student student = null;
		String sql = "select * from Student2 where stId=?";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setStId(Integer.parseInt(rs.getString("stId")));
				student.setStName(rs.getString("stName"));
				student.setStSex(rs.getString("stSex"));
				student.setStAge(rs.getString("stAge"));
				student.setStDept(rs.getString("stDept"));
				student.setStAddress(rs.getString("stAddress"));
				student.setStTel(Long.parseLong(rs.getString("stTel")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
*/
}

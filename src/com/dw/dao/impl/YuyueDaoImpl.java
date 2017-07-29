package com.dw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dw.dao.YuyueDao;
import com.dw.model.Student;
import com.dw.model.Yuyue;
import com.dw.util.DbConn;

public class YuyueDaoImpl implements YuyueDao {
	private Connection conn = DbConn.getConn();
	@Override
	public Yuyue findYuyueByid(int id) {
		Yuyue yuyue = null;
		String sql = "select * from Yuyue where YYID=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int yyid = rs.getInt("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				Long phone = rs.getLong("phone");
				String jztime = rs.getString("jztime");
				yuyue = new Yuyue(yyid, name, sex, age, phone, jztime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yuyue;
	}

	@Override
	public boolean addYuyue(Yuyue yuyue) {
		
				boolean flag = false;
				String sql = "insert into Yuyue(YYID,NAME,SEX,AGE,PHONE,JZTIME) values(?,?,?,?,?,?)";
				try {
					PreparedStatement psmt = conn.prepareStatement(sql);
					psmt.setInt(1, yuyue.getId());
					psmt.setString(2, yuyue.getName());
					psmt.setString(3, yuyue.getSex());
					psmt.setString(4, yuyue.getAge());
					psmt.setLong(5, yuyue.getPhone());
					psmt.setString(6, yuyue.getJztime());
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

	@Override
	public boolean delYuyue(int id) {
		boolean flag = false;
		String sql = "delete from Yuyue where YYID=?";
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

	@Override
	public boolean updateYuyue(Yuyue yuyue) {
		boolean flag = false;
		String sql = "update Yuyue set NAME=?,SEX=?,AGE=?,PHONE=?,JZTIME=? where YYID=? ";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, yuyue.getName());
			psmt.setString(2, yuyue.getSex());
			psmt.setString(3, yuyue.getAge());
			psmt.setLong(4, yuyue.getPhone());
			psmt.setString(5, yuyue.getJztime());
			psmt.setInt(6, yuyue.getId());

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

	@Override
	@SuppressWarnings("unchecked")
	public List YuSelect() {
		
		
			List list = new ArrayList();
			String sql = "select * from Yuyue";
			try {
				Statement smt = conn.createStatement();
				ResultSet rs = smt.executeQuery(sql);
				// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
				while (rs.next()) {
					int yyid = rs.getInt("YYID");
					String name = rs.getString("NAME");
					String sex = rs.getString("SEX");
					String age = rs.getString("AGE");
					Long phone = rs.getLong("PHONE");
					String jztime = rs.getString("JZTIME");
					

					Yuyue yuyue = new Yuyue(yyid, name, sex, age, phone,jztime);
					list.add(yuyue);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		

	}

	@Override
	public Yuyue findYuyueById(String id) {
		// TODO Auto-generated method stub
				Yuyue yuyue = null;
				String sql = "select * from Yuyue where YYID=?";

				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						yuyue = new Yuyue();
						yuyue.setId(Integer.parseInt(rs.getString("YYID")));
						yuyue.setName(rs.getString("NAME"));
						yuyue.setSex(rs.getString("SEX"));
						yuyue.setAge(rs.getString("AGE"));
						yuyue.setPhone(rs.getLong("PHONE"));
						yuyue.setJztime(rs.getString("JZTIME"));
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return yuyue;
			}
	

}

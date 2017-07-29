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

import com.dw.dao.StudentDao;
import com.dw.model.Student;
import com.dw.util.DbConn;

public class StudentDaoImpl implements StudentDao {
	private Connection conn = DbConn.getConn();

	/**
	 * 获取指定的用戶(更新页面操作使用)
	 * 
	 * @param id
	 * @return stu
	 */
	public Student findStudentByid(int id) {
		Student stu = null;
		String sql = "select * from Student2 where stId=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int stId = rs.getInt("stId");
				String stName = rs.getString("stName");
				String stSex = rs.getString("stSex");
				String stAge = rs.getString("stAge");
				Long stTel = rs.getLong("stTel");
				String stDept = rs.getString("stDept");
				String stAddress = rs.getString("stAddress");
				stu = new Student(stId, stName, stSex, stAge, stTel, stDept,
						stAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}

	/**
	 * 添加学生信息
	 * 
	 * @param stu
	 * @retursn flag
	 */
	public boolean addStudent(Student stu) {
		// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
		boolean flag = false;
		String sql = "insert into Student2(stId,stName,stSex,stAge,stTel,stDept,stAddress) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, stu.getStId());
			psmt.setString(2, stu.getStName());
			psmt.setString(3, stu.getStSex());
			psmt.setString(4, stu.getStAge());
			psmt.setLong(5, stu.getStTel());
			psmt.setString(6, stu.getStDept());
			psmt.setString(7, stu.getStAddress());
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
	 * 删除学生信息
	 * 
	 * @param id
	 * @return flag
	 */
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

	/**
	 * 更新学生信息
	 * 
	 * @param stu
	 * @return flag
	 */
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

	/**
	 * 查询全体学生信息
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List StSelect() {
		List list = new ArrayList();
		String sql = "select * from Student2";
		try {
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
			while (rs.next()) {
				int stId = rs.getInt("stId");
				String stName = rs.getString("stName");
				String stSex = rs.getString("stSex");
				String stAge = rs.getString("stAge");
				Long stTel = rs.getLong("stTel");
				String stDept = rs.getString("stDept");
				String stAddress = rs.getString("stAddress");

				Student stu = new Student(stId, stName, stSex, stAge, stTel,
						stDept, stAddress);
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Student findStudentById(String id) {
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

}

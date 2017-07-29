package com.dw.dao;

/**
 * 数据访问层，学生信息CRUD操作
 * @author DY1101shaoyuxian
 */

import java.util.List;

import com.dw.model.Student;


public interface StudentDao {
	
	/**
	 * 获取指定的学生(更新页面操作使用)
	 * 
	 * @param id
	 * @return yuyue
	 */
	public Student findStudentByid(int id);
		

	/**
	 * 添加学生信息
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean addStudent(Student stu);

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 * @return flag
	 */
	public boolean delStudent(int id);

	/**
	 * 更新学生信息
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean updateStudent(Student stu);

	/**
	 * 查询全体学生信息
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List StSelect();
	/**
	 * 根据ID查询学生信息
	 * 
	 * @return Student
	 */
	 public Student findStudentById(String id); 

}

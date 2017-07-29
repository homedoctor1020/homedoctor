package com.dw.dao;

import java.util.List;


import com.dw.model.Yuyue;

public interface YuyueDao {
	/**
	 * ��ȡָ����ԤԼ��(����ҳ�����ʹ��)
	 * 
	 * @param id
	 * @return yuyue
	 */
	public  Yuyue findYuyueByid(int id);
		

	/**
	 * ���ԤԼ����Ϣ
	 * 
	 * @param yuyue
	 * @return flag
	 */
	public boolean addYuyue(Yuyue yuyue);

	/**
	 * ɾ��ԤԼ����Ϣ
	 * 
	 * @param id
	 * @return flag
	 */
	public boolean delYuyue(int id);

	/**
	 * ����ԤԼ����Ϣ
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean updateYuyue(Yuyue yuyue);

	/**
	 * ��ѯȫ��ԤԼ����Ϣ
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List YuSelect();
	/**
	 * ����ID��ѯԤԼ����Ϣ
	 * 
	 * @return Yuyue
	 */
	 public Yuyue findYuyueById(String id); 
}

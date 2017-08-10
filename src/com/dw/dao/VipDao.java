package com.dw.dao;

/**
 * ���ݷ��ʲ㣬ѧ����ϢCRUD����
 * @author DY1101shaoyuxian
 */

import java.util.List;


import com.dw.model.Vip;


public interface VipDao {
	
	/**
	 * ��ȡָ����ѧ��(����ҳ�����ʹ��)
	 * 
	 * @param id
	 * @return yuyue
	 */
	public Vip findVipByOpenid(String id);
		
        /**
         * �����û��ǳƷ���ָ���û�
         * @param nickname
         * @return
         */
	public List findVipByNickname(String nickname);
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param stu
	 * @return flag
	 */
	public boolean addVip(Vip vip);

	/**
	 * ɾ��ѧ����Ϣ
	 * 
	 * @param id
	 * @return flag
	 *//*
	public boolean delVip(String id);

	*//**
	 * ����ѧ����Ϣ
	 * 
	 * @param stu
	 * @return flag
	 *//*
	public boolean updateVip(Vip vip);
*/
	/**
	 * ��ѯȫ��ѧ����Ϣ
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List vipSelect();
	/**
	 * ����ID��ѯѧ����Ϣ
	 * 
	 * @return Student
	 *//*
	 public Vip findVipById(String id); */

}

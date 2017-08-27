package com.dw.dao;

import java.sql.SQLException;
import java.util.List;

import com.dw.model.Resource;

/**
 * �ļ��ϴ����ݿ�����ӿ�
 * @author yongshengXia
 *
 */
public interface ResourceDao {

    /**
     * ����һ���ϴ���¼
     * @param re
     * @return
     * @throws SQLException 
     *  
     */
    public boolean addResource(Resource re) throws SQLException ;
    
    /**
     * ���������ϴ���¼
     * @return
     * @throws SQLException 
     */
    public List selectAll() ;
    /**
     * ����openID�����ϴ���¼
     * @param openid
     * @return
     * @throws SQLException 
     */
    public List findByOpenid(String openid);
}

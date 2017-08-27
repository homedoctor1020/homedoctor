package com.dw.dao;

import java.sql.SQLException;
import java.util.List;

import com.dw.model.Resource;

/**
 * 文件上传数据库操作接口
 * @author yongshengXia
 *
 */
public interface ResourceDao {

    /**
     * 插入一条上传记录
     * @param re
     * @return
     * @throws SQLException 
     *  
     */
    public boolean addResource(Resource re) throws SQLException ;
    
    /**
     * 返回所有上传记录
     * @return
     * @throws SQLException 
     */
    public List selectAll() ;
    /**
     * 根据openID返回上传记录
     * @param openid
     * @return
     * @throws SQLException 
     */
    public List findByOpenid(String openid);
}

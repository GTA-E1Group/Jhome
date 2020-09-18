package com.service.dao;

import com.service.model.Transactionlog;

import java.util.List;

public interface TransactionLogDao {
    /**
     * 保存用户
     * @param Transactionlog
     */
    boolean save(Transactionlog Transactionlog);


    /**
     * 修改
     * @param Transactionlog
     */
    boolean update(Transactionlog Transactionlog);

    /**
     * 删除
     * @param id
     */
    boolean delete(String id);

    /**
     * 查询全部配置
     * @return
     */
    List<Transactionlog> findAll();

    /**
     * 根据条件查询
     * @param
     * @return
     */
    List<Transactionlog> getList(Transactionlog Transactionlog);
}

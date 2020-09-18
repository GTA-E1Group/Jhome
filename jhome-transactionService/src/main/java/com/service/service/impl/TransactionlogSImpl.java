package com.service.service.impl;

import com.bracket.common.Bus.AbstractDaoImpl.BaseDaoImpl;
import com.bracket.common.Bus.AbstractDaoImpl.HttpDaoImpl;
import com.service.dao.TransactionLogDao;
import com.service.model.Transactionlog;
import com.service.service.TransactionlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @description:
 * @author: Daxv
 * @create: 2020-09-17 10:32
 **/
@Service
public class TransactionlogSImpl extends HttpDaoImpl implements TransactionlogService {
    @Autowired
    protected TransactionLogDao transactionLogDao;

    @Override
    public boolean save(Transactionlog transactionlog) {
        return transactionLogDao.save(transactionlog);
    }

    @Override
    public boolean update(Transactionlog transactionLog) {
        return transactionLogDao.update(transactionLog);
    }

    @Override
    public boolean delete(String id) {
        return transactionLogDao.delete(id);
    }

    @Override
    public List<Transactionlog> findAll() {
        return transactionLogDao.findAll();
    }

    @Override
    public List<Transactionlog> getList(Transactionlog Transactionlog) {
        return transactionLogDao.getList(Transactionlog);
    }
}

package com.oyo.hms.txnservice.service;

import com.oyo.hms.txnservice.dto.Txn;
import com.oyo.hms.txnservice.entity.MappingTxns;
import com.oyo.hms.txnservice.entity.Transaction;
import com.oyo.hms.txnservice.repo.TxnMappingRepo;
import com.oyo.hms.txnservice.repo.TxnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TxnService {

    @Autowired
    private TxnRepo txnRepo;

    @Autowired
    private TxnMappingRepo txnMappingRepo;

    @Transactional
    public Boolean insert(long txnId, Txn record) throws Exception {
        if (txnId == 0 || record == null)
            return Boolean.FALSE;

        // check for parent id
        long parentId = record.getParentId() == null ? txnId : record.getParentId();
        if (isTxnExists(txnId)) { // if txn comes second time.
            return insertParentChildMap(parentId, txnId);
        }

        Transaction tn = new Transaction(txnId, record.getAmount(), record.getType(), parentId);
        tn = txnRepo.save(tn);
        if (tn == null || tn.getTxnId() != txnId)
            return false;

        MappingTxns map = new MappingTxns(txnId, txnId, 0);
        // self mapping
        map = txnMappingRepo.save(map);
        if (map == null || map.getId() == 0)
            return false;

        return insertParentChildMap(parentId, txnId);
    }

    boolean insertParentChildMap(long parentId, long childId) {
        int rowsAffected;
        if (parentId != childId) {
            rowsAffected = txnMappingRepo.insert(parentId, childId);
            return rowsAffected > 0;
        }

        return true;
    }

    boolean isTxnExists(long txnId) {
        Transaction tx = txnRepo.findByTxnId(txnId);
        return tx != null;
    }

    public Txn getTxnById(Long txnId) {
        Txn txn = new Txn();
        Transaction tx = txnRepo.findByTxnId(txnId);
        if (tx == null)
            return txn;
        return txn.setAmount(tx.getAmount())
                  .setParentId(tx.getParentId() == txnId ? 0 : tx.getParentId())
                  .setType(tx.getType());
    }

    public List<Long> getTxnByType(String type) {
        List<Long> txnIds = txnRepo.findByType(type);
        Collections.reverse(txnIds);
        return txnIds;
    }

    public double getSum(long txnId) {
        List<Long> childIds = txnMappingRepo.findByParentId(txnId);
        Double su = txnRepo.getSum(childIds);
        return su;
    }
}

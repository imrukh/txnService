package com.oyo.hms.txnservice.repo;

import com.oyo.hms.txnservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TxnRepo extends JpaRepository<Transaction, Integer> {
    Transaction save(Transaction var1);

    @Query(value = "SELECT txn FROM Transaction txn WHERE txn.txnId =:txnId")
    Transaction findByTxnId(@Param("txnId") long txnId);

    @Query(value = "SELECT txn.txnId FROM Transaction txn WHERE txn.type =:type")
    List<Long> findByType(@Param("type") String type);

    @Query(value = "SELECT SUM(txn.amount) FROM Transaction txn WHERE txn.txnId  in :txnIds")
    Double getSum(@Param("txnIds") List<Long> txnIds);
}
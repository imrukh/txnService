package com.oyo.hms.txnservice.repo;

import com.oyo.hms.txnservice.entity.MappingTxns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public interface TxnMappingRepo extends JpaRepository<MappingTxns, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into mapping_txns (parent_id, child_id, depth) " +
            " select p.parent_id, c.child_id, p.depth+c.depth+1" +
            " from mapping_txns p, mapping_txns c" +
            " where p.child_id=:parentId and c.parent_id=:childId", nativeQuery = true)
    int insert(@Param("parentId") long parentId, @Param("childId") long childId);

    MappingTxns save(MappingTxns self);

    @Query(value = "SELECT txn.childId FROM MappingTxns txn WHERE txn.parentId =:parentId")
    List<Long> findByParentId(@Param("parentId") Long parentId);
}
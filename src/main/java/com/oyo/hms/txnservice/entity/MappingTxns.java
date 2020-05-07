package com.oyo.hms.txnservice.entity;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mapping_txns")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MappingTxns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private long parentId;

    @Column(name = "child_id")
    private long childId;

    @Column(name = "depth")
    private int depth;

    public MappingTxns(long parentId, long childId, int depth) {
        this.parentId = parentId;
        this.childId = childId;
        this.depth = depth;
    }
}

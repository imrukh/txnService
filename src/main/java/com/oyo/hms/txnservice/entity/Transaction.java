package com.oyo.hms.txnservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "txn_id")
    private long txnId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private String type;

    @Column(name = "parent_id")
    private long parentId;

    public Transaction(long txnId) {
        super();
        this.txnId = txnId;
    }

    public Transaction(long txnId, double amount, String type, long parentId) {
        super();
        this.txnId = txnId;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }

    public Transaction() {
        super();
    }
}

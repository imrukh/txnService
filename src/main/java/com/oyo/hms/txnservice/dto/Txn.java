package com.oyo.hms.txnservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Txn implements Serializable {
    @JsonProperty(value = "amount")
    private Double amount;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "parent_id")
    private Long parentId;

    public Txn setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Txn setType(String type) {
        this.type = type;
        return this;
    }

    public Txn setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }
}


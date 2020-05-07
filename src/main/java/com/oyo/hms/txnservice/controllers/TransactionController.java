package com.oyo.hms.txnservice.controllers;

import com.oyo.hms.txnservice.dto.Txn;
import com.oyo.hms.txnservice.response.BaseMessageResponse;
import com.oyo.hms.txnservice.response.ServiceResponse;
import com.oyo.hms.txnservice.service.TxnService;
import com.oyo.hms.txnservice.util.Logger;
import com.oyo.hms.txnservice.util.LoggerUtil;
import com.oyo.hms.txnservice.util.LoggingAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "transactionservice")
public class TransactionController {

    @Autowired
    Logger logger;

    @Autowired
    TxnService txnService;

    @PutMapping(value = "transaction/{txnId}")
    public ServiceResponse<?> createRecord(
            @PathVariable("txnId") Long txnId,
            @RequestBody @NotNull Txn txn) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            Boolean status = txnService.insert(txnId, txn);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    status,
                    LoggingAction.Status.SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(new HashMap<String, Boolean>() {
                {
                    put("status", status);
                }
            });
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(
                    new BaseMessageResponse(
                            false, "Failed to create order"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "transaction/{txnId}")
    public Txn getTxn(@PathVariable("txnId") Long txnId) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        Txn txn = null;
        try {
            txn = txnService.getTxnById(txnId);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getTxn,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    txn,
                    LoggingAction.Status.SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getTxn,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );
        }
        return txn;
    }

    @GetMapping(value = "transaction/types/{type}")
    public List<Long> getTxnByType(@PathVariable("type") String type) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        List<Long> txn = null;
        try {
            txn = txnService.getTxnByType(type);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getTxnByType,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    txn,
                    LoggingAction.Status.SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getTxnByType,
                    txn,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );
        }
        return txn;
    }

    @GetMapping(value = "transaction/sum/{txnId}")
    public ServiceResponse<?> getSum(@PathVariable("txnId") Long txnId) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        double sum = 0;
        try {
            sum = txnService.getSum(txnId);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getSum,
                    null,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    sum,
                    LoggingAction.Status.SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.getSum,
                    null,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );
        }

        double finalSum = sum;
        return new ServiceResponse<>(new HashMap<String, Double>() {
            {
                put("sum", finalSum);
            }
        });
    }
}
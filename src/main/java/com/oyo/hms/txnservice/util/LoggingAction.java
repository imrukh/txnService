package com.oyo.hms.txnservice.util;

public class LoggingAction {
    public enum Type {
        CONTROLLER,
        SERVICE,
        MANAGER,
        KAFKA;
    }

    public enum LogType {
        REQUEST,
        RESPONSE;
    }

    public enum Method {
        createRecord,
        getTxn,
        getTxnByType,
        getSum
    }

    public enum Controller {
        TransactionController
    }

    public enum WarnLevel {
        WARN,
        CRITICAL,
        FATAL
    }

    public enum Status {
        EXCEPTION,
        SUCCESS,
    }
}


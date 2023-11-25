package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class TransactionHandler extends GeneralException {
    public TransactionHandler(BaseErrorCode code) {
        super(code);
    }
}

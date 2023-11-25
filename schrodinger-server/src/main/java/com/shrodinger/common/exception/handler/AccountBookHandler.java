package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class AccountBookHandler extends GeneralException {
    public AccountBookHandler(BaseErrorCode code) {
        super(code);
    }
}

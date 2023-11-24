package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode code) {
        super(code);
    }
}

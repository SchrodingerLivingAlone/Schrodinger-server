package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class DiaryHandler extends GeneralException {
    public DiaryHandler(BaseErrorCode code) {
        super(code);
    }
}

package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class NeighborhoodHandler extends GeneralException {
    public NeighborhoodHandler(BaseErrorCode code) {
        super(code);
    }
}

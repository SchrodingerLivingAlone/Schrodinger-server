package com.shrodinger.common.exception.handler;

import com.shrodinger.common.exception.GeneralException;
import com.shrodinger.common.response.BaseErrorCode;

public class NeighborhoodPostHandler extends GeneralException {
    public NeighborhoodPostHandler(BaseErrorCode code) {
        super(code);
    }
}

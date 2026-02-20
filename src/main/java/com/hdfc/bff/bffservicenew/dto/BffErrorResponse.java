package com.hdfc.bff.bffservicenew.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BffErrorResponse {

    private boolean success;
    private String errorCode;
    private String errMessage;
}

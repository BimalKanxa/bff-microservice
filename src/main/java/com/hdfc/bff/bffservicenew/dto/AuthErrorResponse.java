package com.hdfc.bff.bffservicenew.dto;

import lombok.Data;

@Data
public class AuthErrorResponse {

    private Status status;

    @Data
    public static class Status {
        private String message;
        private String code;
    }
}

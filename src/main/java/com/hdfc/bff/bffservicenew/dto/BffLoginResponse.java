package com.hdfc.bff.bffservicenew.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BffLoginResponse {

    private boolean success;

    // For success response
    private ResponseData responseData;

    // For error response
    private String errorCode;
    private String errMessage;

    @Data
    @Builder
    public static class ResponseData {
        private String role;
        private String accessToken;
        private Profile profile;
    }

    @Data
    @Builder
    public static class Profile {
        private String userId;
        private String phone;
        private String email;
    }
}

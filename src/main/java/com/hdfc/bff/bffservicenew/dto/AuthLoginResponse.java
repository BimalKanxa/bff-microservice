package com.hdfc.bff.bffservicenew.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthLoginResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("role")
    private String role;

    @JsonProperty("status")
    private String status;

    @JsonProperty("profile")
    private UserProfile profile;
}

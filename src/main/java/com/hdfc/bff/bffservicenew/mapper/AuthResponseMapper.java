package com.hdfc.bff.bffservicenew.mapper;

import com.hdfc.bff.bffservicenew.dto.*;
import org.springframework.stereotype.Component;

@Component
public class AuthResponseMapper {

    public BffLoginResponse transform(AuthLoginResponse authResponse) {

        if (authResponse == null
                || authResponse.getCode() == null
                || authResponse.getCode() != 200
                || !"success".equalsIgnoreCase(authResponse.getStatus())
                || authResponse.getProfile() == null
                || authResponse.getAccessToken() == null
                || authResponse.getAccessToken().isBlank()) {

            return BffLoginResponse.builder()
                    .success(false)
                    .build();
        }

        return BffLoginResponse.builder()
                .success(true)
                .responseData(
                        BffLoginResponse.ResponseData.builder()
                                .role(authResponse.getRole())
                                .accessToken(authResponse.getAccessToken())
                                .profile(
                                        BffLoginResponse.Profile.builder()
                                                .userId(authResponse.getProfile().getUserId())
                                                .phone(authResponse.getProfile().getPhone())
                                                .email(authResponse.getProfile().getEmail())
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}

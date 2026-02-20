package com.hdfc.bff.bffservicenew.client;

import com.hdfc.bff.bffservicenew.dto.AuthLoginResponse;
import com.hdfc.bff.bffservicenew.dto.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "auth-service",
        url = "${auth.service.url}"
)
public interface AuthServiceClient {
    @PostMapping("/api/auth/login")

    AuthLoginResponse login(@RequestBody LoginRequest request);
}

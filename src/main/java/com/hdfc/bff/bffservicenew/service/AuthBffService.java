package com.hdfc.bff.bffservicenew.service;

import com.hdfc.bff.bffservicenew.client.AuthServiceClient;
import com.hdfc.bff.bffservicenew.dto.*;
import com.hdfc.bff.bffservicenew.mapper.AuthResponseMapper;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AuthBffService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthServiceClient authServiceClient;
    private final AuthResponseMapper mapper;
    private static final Logger logger =
            LoggerFactory.getLogger(AuthBffService.class);


    public AuthBffService(AuthServiceClient authServiceClient,
                          AuthResponseMapper mapper) {
        this.authServiceClient = authServiceClient;
        this.mapper = mapper;
    }

    public BffLoginResponse login(LoginRequest request) {
        logger.info("Login request received for email: {}", request.getUsername());


            AuthLoginResponse authResponse =
                    authServiceClient.login(request);

            logger.info("Response received from Auth Service. Status: {}, Code: {}",
                    authResponse.getStatus(),
                    authResponse.getCode());

            return mapper.transform(authResponse);


    }
}

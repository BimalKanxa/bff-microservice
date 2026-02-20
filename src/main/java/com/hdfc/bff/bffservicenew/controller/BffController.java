package com.hdfc.bff.bffservicenew.controller;

import com.hdfc.bff.bffservicenew.dto.BffErrorResponse;
import com.hdfc.bff.bffservicenew.dto.BffLoginResponse;
import com.hdfc.bff.bffservicenew.dto.LoginRequest;
import com.hdfc.bff.bffservicenew.service.AuthBffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/bff")
public class BffController {

    private static final Logger logger =
            LoggerFactory.getLogger(BffController.class);

    private final AuthBffService authBffService;

    public BffController(AuthBffService authBffService) {
        this.authBffService = authBffService;
    }

    @PostMapping("/login")
    public ResponseEntity<BffLoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest servletRequest

    ) {
        logger.info("Incoming login request at path: {}",
                servletRequest.getRequestURI());

        BffLoginResponse response = authBffService.login(request);



        logger.info("Login process completed. Success: {}",
                response.isSuccess());

        return ResponseEntity.ok(response);
    }
}

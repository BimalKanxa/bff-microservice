
package com.hdfc.bff.bffservicenew.controller;


import com.hdfc.bff.bffservicenew.client.AuthServiceClient;
import com.hdfc.bff.bffservicenew.dto.AuthLoginResponse;
import com.hdfc.bff.bffservicenew.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff")

public class BffController {
    private final AuthServiceClient authServiceClient;

    public BffController(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    /**
     * Login endpoint
     * Client -> BFF -> Auth Service
     */

    @CrossOrigin(origins = "https://hdfc-support-app-l1vcw34qd-mahmudul-hassans-projects-566e9814.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody LoginRequest request) {

        AuthLoginResponse response = authServiceClient.login(request);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/health")
    public String healthCheck(){
        return "BFF is Running Healthy";
    }
}

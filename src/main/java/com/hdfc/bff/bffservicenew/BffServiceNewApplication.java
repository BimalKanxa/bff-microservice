package com.hdfc.bff.bffservicenew;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
        info = @Info(
                title = "BFF API FOR UNIFIED LOG ANALYSIS",
                version = "1.0",
                description = "API documentation for managing bff api"
        )
)
@SpringBootApplication
@EnableFeignClients
public class BffServiceNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffServiceNewApplication.class, args);
    }

}

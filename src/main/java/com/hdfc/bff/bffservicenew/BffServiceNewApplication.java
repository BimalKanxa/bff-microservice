package com.hdfc.bff.bffservicenew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffServiceNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffServiceNewApplication.class, args);
    }

}

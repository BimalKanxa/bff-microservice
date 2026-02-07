package com.hdfc.bff.bffservicenew.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "customer-journey-service",
        url = "${journey.service.url}"
)
public interface CustomerJourneyClient {

    @GetMapping("/api/v1/get-journey-list")
    Map<String, Object> getJourneyList(
            @RequestParam("customerId") String customerId
    );

    @GetMapping("/api/v1/get-stages")
    Map<String, Object> getJourneyStages(
            @RequestParam("journeyId") String journeyId
    );

}

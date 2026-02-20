package com.hdfc.bff.bffservicenew.client;


import com.hdfc.bff.bffservicenew.dto.JourneyListRequest;
import com.hdfc.bff.bffservicenew.dto.JourneyStagesRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "customer-journey-service",
        url = "${journey.service.url}"
)
public interface CustomerJourneyClient {

    @PostMapping("/api/v1/journeys")
   Object getJourneyList(@RequestBody JourneyListRequest request);

    @PostMapping("/api/v1/journeys/stages")
   Object getJourneyStages(@RequestBody JourneyStagesRequest request);

}

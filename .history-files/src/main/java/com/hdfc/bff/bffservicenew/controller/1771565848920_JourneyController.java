//package com.hdfc.bff.bffservicenew.controller;
//
//
//import com.hdfc.bff.bffservicenew.client.CustomerJourneyClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * JourneyController
// * ------------------
// * BFF endpoints for journey list and journey stages.
// * Frontend interacts ONLY with this controller.
// */
//
//@RestController
//@RequestMapping("/bff/journeys")
//public class JourneyController {
//
//    private final CustomerJourneyClient journeyClient;
//
//    public JourneyController(CustomerJourneyClient journeyClient) {
//        this.journeyClient = journeyClient;
//    }
//
//    /**
//     * Get journey list for a customer
//     */
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getJourneyList(
//            @RequestParam String customerId
//    ) {
//        return ResponseEntity.ok(
//                journeyClient.getJourneyList(customerId)
//        );
//    }
//
//    /**
//     * Get journey stages by journeyId
//     */
//    @GetMapping("/{journeyId}/stages")
//    public ResponseEntity<Map<String, Object>> getJourneyStages(
//            @PathVariable String journeyId
//    ) {
//        return ResponseEntity.ok(
//                journeyClient.getJourneyStages(journeyId)
//        );
//    }
//
//}

package com.hdfc.bff.bffservicenew.controller;

import com.hdfc.bff.bffservicenew.client.CustomerJourneyClient;
import com.hdfc.bff.bffservicenew.dto.JourneyListRequest;
import com.hdfc.bff.bffservicenew.dto.JourneyStagesRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/journeys")
public class JourneyController {

    private final CustomerJourneyClient journeyClient;

    public JourneyController(CustomerJourneyClient journeyClient) {
        this.journeyClient = journeyClient;
    }

    /**
     * Get journey list
     */
    @PostMapping("/list")
    public ResponseEntity<Object> getJourneyList(
            @RequestBody JourneyListRequest request
    ) {
        return ResponseEntity.ok(
                journeyClient.getJourneyList(request)
        );
    }

    /**
     * Get journey stages
     */
    @PostMapping("/stages")
    public ResponseEntity<Object> getJourneyStages(
            @RequestBody JourneyStagesRequest request
    ) {
        return ResponseEntity.ok(
                journeyClient.getJourneyStages(request)
        );
    }
}

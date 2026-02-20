package com.hdfc.bff.bffservicenew.dto;

import lombok.Data;

@Data
public class JourneyStagesRequest {

    private String journeyId;
    private int page;
    private int pageSize;
    private String order;
}

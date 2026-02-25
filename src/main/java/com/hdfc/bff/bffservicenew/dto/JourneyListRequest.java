package com.hdfc.bff.bffservicenew.dto;

import lombok.Data;

@Data
public class JourneyListRequest {

    private String customerId;
    private int page;
    private int pageSize;
    private String order;
}

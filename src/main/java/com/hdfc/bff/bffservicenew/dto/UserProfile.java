package com.hdfc.bff.bffservicenew.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



    public class UserProfile {

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("user_id")
        private String userId;

}

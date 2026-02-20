package com.hdfc.bff.bffservicenew.exceptionHandler;


import com.hdfc.bff.bffservicenew.dto.BffErrorResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BffErrorResponse> handleFeignException(FeignException ex) {

        String errorCode = String.valueOf(ex.status());
        String errMessage = "Invalid Credentials";

        try {

            String responseBody = ex.contentUTF8();

            logger.error("Feign error raw body: {}", responseBody);

            if (responseBody != null && !responseBody.isEmpty()) {

                JsonNode root = objectMapper.readTree(responseBody);

                // 🔹 AUTH SERVICE FORMAT
                if (root.has("status") && root.get("status").isObject()) {
//                    System.out.println("inside root status");

                    JsonNode statusNode = root.get("status");

                    if (statusNode.has("message")) {
                        errMessage = statusNode.get("message").asString();
                    }

                    if (statusNode.has("code")) {
                        errorCode = statusNode.get("code").asString();
                    }
                }

                // 🔹 JOURNEY SERVICE FORMAT
                else if (root.has("success") && root.has("responseData")) {

                    JsonNode responseData = root.get("responseData");

                    if (responseData.has("message")) {
                        errMessage = responseData.get("message").asText();
                    }
                }

                // 🔹 SPRING DEFAULT ERROR FORMAT
                else if (root.has("message")) {
                    errMessage = root.get("message").asText();
                }
            }

        } catch (Exception parseException) {
            logger.error("Error parsing Feign response", parseException);
        }

        BffErrorResponse errorResponse = BffErrorResponse.builder()
                .success(false)
                .errorCode(errorCode)
                .errMessage(errMessage)
                .build();

        return ResponseEntity.status(ex.status()).body(errorResponse);
    }
}

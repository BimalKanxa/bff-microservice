package com.hdfc.bff.bffservicenew.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * ResilienceConfig
 * -----------------
 * Configures Circuit Breaker behavior for downstream services.
 */
@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {

        return CircuitBreakerConfig.custom()
                // Failure rate threshold: 50%
                .failureRateThreshold(50)

                // Wait time before transitioning from OPEN → HALF_OPEN
                .waitDurationInOpenState(Duration.ofSeconds(10))

                // Number of calls in half-open state
                .permittedNumberOfCallsInHalfOpenState(3)

                // Sliding window size
                .slidingWindowSize(5)

                // Minimum calls before calculating failure rate
                .minimumNumberOfCalls(3)

                .build();
    }

}

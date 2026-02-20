package com.hdfc.bff.bffservicenew.health;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

/**
 * AuthServiceHealthIndicator
 * ---------------------------
 * Implements:
 * - Timeout (RestTemplate)
 * - Retry (Resilience4j)
 * - Circuit Breaker (Resilience4j)
 *
 * Handles temporary failures like:
 * - 502 Bad Gateway
 * - Service cold start
 */

@Component
public class AuthServiceHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;

    @Value("${auth.service.health.url}")
    private String authHealthUrl;

    public AuthServiceHealthIndicator(
            RestTemplate restTemplate,
            CircuitBreakerRegistry registry,
            RetryRegistry retryRegistry
            ) {

        this.restTemplate = restTemplate;
        // Create or retrieve circuit breaker named "authServiceCB"
        this.circuitBreaker = registry.circuitBreaker("authServiceCB");
        this.retry = retryRegistry.retry("authServiceRetry");
    }

    @Override
    public Health health() {

        Supplier<Health> decoratedSupplier =
                CircuitBreaker.decorateSupplier(
                        circuitBreaker,
                        Retry.decorateSupplier(retry, this::checkAuthHealth)

                );

        try {
            return decoratedSupplier.get();

        } catch (Exception ex) {
            return Health.down()
                    .withDetail("authService", "DOWN")
                    .withDetail("circuitBreakerState",
                            circuitBreaker.getState().name())
                    .withDetail("error", ex.getMessage())
                    .build();
        }
    }

    /**
     * Actual health check call to Auth Service
     */
    private Health checkAuthHealth() {

        restTemplate.getForEntity(authHealthUrl, String.class);

        return Health.up()
                .withDetail("authService", "UP")
                .withDetail("circuitBreakerState",
                        circuitBreaker.getState().name())
                .build();
    }
}

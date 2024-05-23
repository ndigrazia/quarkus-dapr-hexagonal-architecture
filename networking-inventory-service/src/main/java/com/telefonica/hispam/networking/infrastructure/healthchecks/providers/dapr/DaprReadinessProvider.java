package com.telefonica.hispam.networking.infrastructure.healthchecks.providers.dapr;

import io.quarkiverse.dapr.core.SyncDaprClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import com.telefonica.hispam.networking.infrastructure.healthchecks.providers.ReadinessProvider;

@ApplicationScoped
public class DaprReadinessProvider implements ReadinessProvider {
    
    private static final String DEFAULT_TIMEOUT = "5000";

    @Inject
    SyncDaprClient dapr;

    @ConfigProperty(name = "quarkus.dapr.sidecar.timeout", 
        defaultValue = DEFAULT_TIMEOUT) 
    String timeout;

    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = 
            HealthCheckResponse.named("Dapr connection health check");

        (!sidecarCheck()?responseBuilder.down():responseBuilder.up())
            .withData("Dapr Sidecar", (!sidecarCheck()?"DOWN":"UP"));

        return responseBuilder.build();
    }

    private boolean sidecarCheck() {
        try { 
            dapr.waitForSidecar(tryParse(timeout));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Integer tryParse(String timeout) {
        try {
            return Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            return Integer.parseInt(DEFAULT_TIMEOUT);
        }
    }
    
}
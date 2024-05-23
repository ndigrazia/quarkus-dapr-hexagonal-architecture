package com.telefonica.hispam.networking.infrastructure.healthchecks;

import jakarta.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import org.eclipse.microprofile.health.Readiness;

import com.telefonica.hispam.networking.infrastructure.healthchecks.providers.ReadinessProvider;

import jakarta.enterprise.inject.Instance;

@Readiness
public class ReadinessCheck implements HealthCheck {
    
    @Inject
    Instance<ReadinessProvider> readinessProvider;

    public HealthCheckResponse call() {
        return readinessProvider.get().call();
    }

}
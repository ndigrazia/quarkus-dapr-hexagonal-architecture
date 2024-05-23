package com.telefonica.hispam.networking.infrastructure.healthchecks.providers;

import org.eclipse.microprofile.health.HealthCheckResponse;

public interface ReadinessProvider {
 
     public HealthCheckResponse call();
}

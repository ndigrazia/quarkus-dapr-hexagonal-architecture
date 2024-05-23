package com.telefonica.hispam.routerInventory.infrastructure.healthchecks.providers;

import org.eclipse.microprofile.health.HealthCheckResponse;

public interface ReadinessProvider {
 
     public HealthCheckResponse call();
}

package com.telefonica.hispam.networking.infrastructure.healthchecks;

import java.util.Date;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessCheck implements HealthCheck {
    
    private static final String APP_VERSION = "1.0.0-SNAPSHOT";

    @ConfigProperty(name = "quarkus.application.version",
        defaultValue =  APP_VERSION)
    String version;

    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
            .name(applicationName)
            .up()//.down()
            .withData("version", version)
            .withData("systemTime", Long.toString(new Date().getTime()))
            .build();
    }
}
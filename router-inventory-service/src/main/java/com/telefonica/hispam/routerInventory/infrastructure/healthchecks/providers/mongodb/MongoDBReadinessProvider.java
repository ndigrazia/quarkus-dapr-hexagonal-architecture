package com.telefonica.hispam.routerInventory.infrastructure.healthchecks.providers.mongodb;

import com.mongodb.client.MongoClient;
import com.telefonica.hispam.routerInventory.infrastructure.healthchecks.providers.ReadinessProvider;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

@ApplicationScoped
@LookupIfProperty(name = "health.check.implementation-name", 
    stringValue = "panache-mongodb")
public class MongoDBReadinessProvider implements ReadinessProvider {
    
    private static final String DEFAULT_DATABASE = "router-inventory-store";

    @Inject
    MongoClient mongoClient;

    @ConfigProperty(name = "quarkus.mongodb.database", 
        defaultValue = DEFAULT_DATABASE) 
    String database;

    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = 
            HealthCheckResponse.named("MongoDB connection health check");

        try {
            mongoClient.getDatabase(database).listCollections().first();
            responseBuilder.up();
        } catch (Exception e) {
            responseBuilder.down().withData("error", 
                "Connection refused.");
        }

        return responseBuilder.build();
    }
}
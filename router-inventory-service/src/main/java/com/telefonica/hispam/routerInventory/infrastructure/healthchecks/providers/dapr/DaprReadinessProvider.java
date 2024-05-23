package com.telefonica.hispam.routerInventory.infrastructure.healthchecks.providers.dapr;

import io.quarkiverse.dapr.core.SyncDaprClient;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import com.telefonica.hispam.routerInventory.infrastructure.healthchecks.providers.ReadinessProvider;
import com.telefonica.hispam.routerInventory.infrastructure.util.DaprUtil;

@ApplicationScoped
@LookupIfProperty(name = "health.check.implementation-name", 
    stringValue = "dapr")
public class DaprReadinessProvider implements ReadinessProvider {
    
    private static final String DEFAULT_TIMEOUT = "5000";

    @Inject
    SyncDaprClient dapr;

    @ConfigProperty(name = "quarkus.dapr.sidecar.timeout", 
        defaultValue = DEFAULT_TIMEOUT) 
    String timeout;

    @ConfigProperty(name = "quarkus.dapr.store.name", 
    defaultValue = DaprUtil.STATE_STORE_DEFAULT)
    String stateStoreName;

    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = 
            HealthCheckResponse.named("Dapr connection health check");

        (!sidecarCheck()?responseBuilder.down():responseBuilder.up())
            .withData("Dapr Sidecar", (!sidecarCheck()?"DOWN":"UP"));

        (!stateStoreCheck()?responseBuilder.down():responseBuilder.up())
            .withData("State Store", (!stateStoreCheck()?"DOWN":"UP"));
    
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

    private boolean stateStoreCheck() {
        try { 
            /*dapr.saveState(stateStoreName,
               stateStoreName.concat("-health-check"),
                    LocalDateTime.now());*/
            dapr.getState(stateStoreName,
                    stateStoreName.concat("-health-check"),
                    LocalDateTime.class);
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
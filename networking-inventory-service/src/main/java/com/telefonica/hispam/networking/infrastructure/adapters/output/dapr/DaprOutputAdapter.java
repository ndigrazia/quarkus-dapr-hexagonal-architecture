package com.telefonica.hispam.networking.infrastructure.adapters.output.dapr;

import java.util.concurrent.CompletableFuture;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.telefonica.hispam.networking.application.ports.output.NetworkingMgmtOutputPort;
import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.util.Util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.dapr.client.domain.HttpExtension;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkiverse.dapr.core.SyncDaprClient;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;

@ApplicationScoped
public class DaprOutputAdapter implements NetworkingMgmtOutputPort {

    private static final Logger LOG = Logger.getLogger(DaprOutputAdapter.class);

    private static final String ROUTER_SERVICE_NAME = "router-inventory-service";

    @Inject
    SyncDaprClient dapr;

    // TODO Remove the following lines if you need to persist in a Dapr store
    @Inject 
    @CacheName("demo-cache")
    Cache cache;

    @ConfigProperty(name = "quarkus.dapr.router.service.name", 
         defaultValue = ROUTER_SERVICE_NAME)
    String serviceName;

    @Override
    public Network persistNetwork(Network network) {
        // TODO if you need to persist the network in a Dapr store, do it here
        // Please, replace the following line with your code
        cache.as(CaffeineCache.class).put(network.getId().getUuid().toString(), 
            CompletableFuture.completedFuture(network));
        return network;
    }

    @Override
    @WithSpan("callingDaprClientToPersistRouter")
    public Router persistRouter(Router router) {
        LOG.info("Calling Dapr client to persist router: " + Util.toJson(router));

        var added = dapr.invokeMethod(serviceName, "routers", 
             router, HttpExtension.POST, null, Router.class);

        LOG.info("Router persisted: " + Util.toJson(added));

        return added;
    }

    @Override
    public Network getNetwork(Id id) {
        // TODO if you need to get the network from a Dapr store, do it here
        // Please, replace the following line with your code
        CompletableFuture<Network> fetched = cache.as(CaffeineCache.class)
            .getIfPresent(id.getUuid().toString());
        return fetched==null?null:fetched.join();
    }

}

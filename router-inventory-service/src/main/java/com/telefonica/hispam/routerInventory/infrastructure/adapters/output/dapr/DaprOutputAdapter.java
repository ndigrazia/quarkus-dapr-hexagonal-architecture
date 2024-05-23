package com.telefonica.hispam.routerInventory.infrastructure.adapters.output.dapr;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.telefonica.hispam.routerInventory.application.ports.output.RouterMgmtOutputPort;
import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.infrastructure.util.DaprUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.dapr.client.domain.State;
import io.dapr.exceptions.DaprException;

import io.opentelemetry.instrumentation.annotations.WithSpan;

import io.quarkiverse.dapr.core.SyncDaprClient;
import io.quarkus.arc.lookup.LookupIfProperty;

@ApplicationScoped
@LookupIfProperty(name = "service.implementation-name", stringValue = "dapr")
public class DaprOutputAdapter implements RouterMgmtOutputPort {

    @Inject
    SyncDaprClient dapr;

    @ConfigProperty(name = "quarkus.dapr.store.name", 
    defaultValue = DaprUtil.STATE_STORE_DEFAULT)
    String stateStoreName;

    @Override
    @WithSpan("persistRouterOnStateStore")
    public Router persistRouter(Router router) {
        if (router == null) return null;
        dapr.saveState(stateStoreName, 
            router.getId().getUuid().toString(), router);
        return router;
    }

    @Override
    public void retireRouter(Id id) {
        dapr.deleteState(stateStoreName, id.getUuid().toString());
    }

    @Override
    public Router retrieveRouter(Id id) {
        try {
            State<Router> router = dapr.getState(stateStoreName,
                    id.getUuid().toString(), Router.class);
            return router.getValue();
        } catch (DaprException e) {
           if(e.getMessage().contains("No content")) return null;
           throw   e;
        }
    }

    @Override
    public  boolean existsRouter(Id id) {
       return retrieveRouter(id) != null;
    }

    @Override
    public List<Router> listRouters() {
        throw new UnsupportedOperationException(
            "Unimplemented method 'listRouters'");
    }
    
}
package com.telefonica.hispam.routerInventory.application.ports.input;

import java.util.List;

import com.telefonica.hispam.routerInventory.application.ports.output.RouterMgmtOutputPort;
import com.telefonica.hispam.routerInventory.application.usescases.RouterMgmtUseCase;
import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.exception.RouterException;
import com.telefonica.hispam.routerInventory.domain.service.RouterService;
import com.telefonica.hispam.routerInventory.domain.vo.Id;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class RouterMgmtInputPort implements RouterMgmtUseCase {

    @Inject
    Instance<RouterMgmtOutputPort> routerMgmOutputPort;

    @Override
    public Router addRouter(Router router) {
        return RouterService.addRouter(router);
    } 

    @Override
    public Router persistRouter(Router router) {
        if (!existsRouter(router.getId()))
            return routerMgmOutputPort.get().persistRouter(router);  
            
        throw RouterException.create("Router already exists");
    }

    @Override
    public void retireRouter(Id id) {
        routerMgmOutputPort.get().retireRouter(id);
    }

    @Override
    public Router retrieveRouter(Id id) {
        return routerMgmOutputPort.get().retrieveRouter(id);
    }
    
    @Override
    public boolean existsRouter(Id id) {  
        return routerMgmOutputPort.get().existsRouter(id);
    }

    @Override
    public List<Router> listCoreRouters() {
        var routers = routerMgmOutputPort.get().listRouters();
        return RouterService.listCoreRouters(routers);
    }
    
}

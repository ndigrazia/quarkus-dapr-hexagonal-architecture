package com.telefonica.hispam.routerInventory.domain.service;

import java.util.List;

import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.entity.factory.RouterFactory;
import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.domain.vo.Location;
import com.telefonica.hispam.routerInventory.domain.vo.Type;
import com.telefonica.hispam.routerInventory.domain.vo.Vendor;

public class RouterService {

     public static Router addRouter(
        String name,
        Location location,
        Type type,
        Vendor vendor
    ) {
        return RouterFactory.create(name, location, type, vendor);
    }

    public static Router addRouter(Router router) {
        router.setId(Id.generate());
        return router;
    }

    public static List<Router> listCoreRouters(List<Router> routers) {
        return routers.stream().filter(router -> router.getType().equals(Type.CORE)).toList();
    }
    
}

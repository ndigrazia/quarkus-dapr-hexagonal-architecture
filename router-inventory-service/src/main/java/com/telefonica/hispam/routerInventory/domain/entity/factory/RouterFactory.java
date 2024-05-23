package com.telefonica.hispam.routerInventory.domain.entity.factory;

import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.domain.vo.Location;
import com.telefonica.hispam.routerInventory.domain.vo.Type;
import com.telefonica.hispam.routerInventory.domain.vo.Vendor;

public class RouterFactory {

    public static Router create(String name, Location location, Type type, Vendor vendor) {
        return Router.builder().id(Id.generate()).name(name)
            .location(location).vendor(vendor).type(type).build();
    }

}

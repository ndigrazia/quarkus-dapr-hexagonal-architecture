package com.telefonica.hispam.networking.domain.entity.factory;

import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Type;
import com.telefonica.hispam.networking.domain.vo.Vendor;

public class RouterFactory {

    public static Router create(String name, Location location, Type type, Vendor vendor) {
        return Router.builder().name(name)
            .location(location).vendor(vendor).type(type).build();
    }

}

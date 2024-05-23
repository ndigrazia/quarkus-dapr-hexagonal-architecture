package com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.mappers;

import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.domain.vo.Location;
import com.telefonica.hispam.routerInventory.domain.vo.Type;
import com.telefonica.hispam.routerInventory.domain.vo.Vendor;
import com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.data.RouterDocument;

public class PanacheMongoDBMapper {
    
    public static RouterDocument toDocument(Router domain) {
        if(domain == null) return null;
        
        var data = new RouterDocument();

        data.uuid = domain.getId().getUuid().toString();

        data.name = domain.getName();

        data.address = domain.getLocation().getAddress();
        data.city = domain.getLocation().getCity();
        data.state = domain.getLocation().getState();
        data.zipCode = domain.getLocation().getZipCode();
        data.country = domain.getLocation().getCountry();
        data.latitude = domain.getLocation().getLatitude();
        data.longitude = domain.getLocation().getLongitude();

        data.vendor = domain.getVendor().toString();

        data.type = domain.getType().toString();

        return data;
    }

    public static Router toDomain(RouterDocument doc) {
        if(doc == null) return null;

        var domain = Router.builder()
            .id(Id.generateWith(doc.uuid))
            .name(doc.name)
            .location(Location.builder()
                .address(doc.address)
                .city(doc.city)
                .state(doc.state)
                .zipCode(doc.zipCode)
                .country(doc.country)
                .latitude(doc.latitude)
                .longitude(doc.longitude)
                .build())
            .type(Type.valueOf(doc.type))
            .vendor(Vendor.valueOf(doc.vendor))
            .build();

        return domain;
    }

}

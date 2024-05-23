package com.telefonica.hispam.networking.domain.service;

import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.entity.factory.NetworkFactory;
import com.telefonica.hispam.networking.domain.entity.factory.RouterFactory;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Topology;
import com.telefonica.hispam.networking.domain.vo.Type;
import com.telefonica.hispam.networking.domain.vo.Vendor;

public class NetworkService {

     public static Network addNetwork(
        String name,
        Location location,
        Topology topology
    ) {
        return NetworkFactory.create(name, location, topology);
    }

    public static Router addRouter(
        String name,
        Location location, 
        Type type, 
        Vendor vendor
    ){
       return RouterFactory.create(name, location,
             type, vendor);
    }
    
}

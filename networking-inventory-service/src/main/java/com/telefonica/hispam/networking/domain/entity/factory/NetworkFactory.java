package com.telefonica.hispam.networking.domain.entity.factory;

import java.util.ArrayList;

import com.telefonica.hispam.networking.domain.entity.Device;
import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Topology;

public class NetworkFactory {
    
     public static Network create(String name, Location location, Topology topology) {
        return Network.builder().id(Id.generate()).name(name)
                .location(location).devices(new ArrayList<Device>())
                    .topology(topology).build();
    }

}

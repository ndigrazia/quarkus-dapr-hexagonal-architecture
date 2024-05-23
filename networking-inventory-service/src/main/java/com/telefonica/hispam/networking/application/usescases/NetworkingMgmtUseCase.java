package com.telefonica.hispam.networking.application.usescases;

import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Topology;
import com.telefonica.hispam.networking.domain.vo.Type;
import com.telefonica.hispam.networking.domain.vo.Vendor;

public interface NetworkingMgmtUseCase {  
      
    public Network makeUpNetworkTopology(
        String name,
        Location location,
        Topology topology
    );

    public Router addRouterToNetwork(
        String name, 
        Type type, 
        Vendor vendor, 
        Network network
    );

    public Network getNetwork(Id id);
       
}
package com.telefonica.hispam.networking.application.ports.output;

import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.vo.Id;

public interface NetworkingMgmtOutputPort {

    public Network persistNetwork(Network network);
    
    public Router persistRouter(Router router);

    public Network getNetwork(Id id);
       
}

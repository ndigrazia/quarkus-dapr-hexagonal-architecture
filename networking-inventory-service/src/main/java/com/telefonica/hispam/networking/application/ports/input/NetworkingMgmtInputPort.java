package com.telefonica.hispam.networking.application.ports.input;

import com.telefonica.hispam.networking.application.ports.output.NetworkingMgmtOutputPort;
import com.telefonica.hispam.networking.application.usescases.NetworkingMgmtUseCase;
import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.service.NetworkService;
import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Topology;
import com.telefonica.hispam.networking.domain.vo.Type;
import com.telefonica.hispam.networking.domain.vo.Vendor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class NetworkingMgmtInputPort implements NetworkingMgmtUseCase {

    @Inject
    Instance<NetworkingMgmtOutputPort> networkingMgmOutputPort;

    @Override
    public Network makeUpNetworkTopology(String name, Location location, Topology topology) {
        var network = NetworkService.addNetwork(name, location, topology);
        var added = networkingMgmOutputPort.get().persistNetwork(network);
        return added;
    }

    @Override
    public Router addRouterToNetwork(String name, Type type, Vendor vendor, Network network) {
        var router = NetworkService.addRouter(name, network.getLocation(), type, vendor);
        var added = networkingMgmOutputPort.get().persistRouter(router);
        network.getDevices().add(added);
        return added;
    }

    @Override
    public Network getNetwork(Id id) {
        return networkingMgmOutputPort.get().getNetwork(id);
    }

}

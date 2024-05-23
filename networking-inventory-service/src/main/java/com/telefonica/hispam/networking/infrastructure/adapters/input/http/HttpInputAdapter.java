package com.telefonica.hispam.networking.infrastructure.adapters.input.http;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import com.telefonica.hispam.networking.application.usescases.NetworkingMgmtUseCase;
import com.telefonica.hispam.networking.domain.entity.Network;
import com.telefonica.hispam.networking.domain.entity.Router;
import com.telefonica.hispam.networking.domain.exception.NetworkingException;
import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Topology;

import com.telefonica.hispam.networking.util.Util;

import org.eclipse.microprofile.metrics.annotation.Counted;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/computer-networking")
public class HttpInputAdapter {
    
    private static final Logger LOG = Logger.getLogger(HttpInputAdapter.class);

    @Inject
    NetworkingMgmtUseCase networkingMgmtUseCase;

    @POST
    @Path("/mesh-topology/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(description = "How many mesh topology return", name = "numberCounted")
    public Response makeUpMeshTopology(Network network) {
        try {
            LOG.info("Add network: " + Util.toJson(network));
            var added = networkingMgmtUseCase.makeUpNetworkTopology(network.getName(),
                network.getLocation(), Topology.MESH);
            LOG.info("Network added: " + Util.toJson(added));

            return  Response.status(Response.Status.CREATED).entity(added).build();
        } catch (NetworkingException e) {
            LOG.error("Network not added: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e.toJson()).build();
        } catch (Exception e) {
            LOG.error("Network not added: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e).build();
        }
    }

    @POST
    @Path("/{id}/routers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRouterToNetwork(@RestPath String id, Router router) {
        try {
            var fetched = networkingMgmtUseCase.getNetwork(Id.generateWith(id));
        
            if (fetched != null) {
                LOG.info("Add router: " + Util.toJson(router));
                var added = networkingMgmtUseCase.addRouterToNetwork(router.getName(), 
                    router.getType(), router.getVendor(), fetched); 
                LOG.info("Router added: " + Util.toJson(added));
                return Response.status(Response.Status.CREATED).entity(added).build();
            }

            return Response.status(Response.Status.NOT_FOUND).build();    
        } catch (IllegalArgumentException e) {
            LOG.error("Router not added: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                        .entity(e).build();
        } catch (Exception e) {
            LOG.error("Router not added: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e).build();
        }
    }
    
}

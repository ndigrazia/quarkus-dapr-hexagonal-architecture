package com.telefonica.hispam.routerInventory.infrastructure.adapters.input.http;

import org.jboss.logging.Logger;

import com.telefonica.hispam.routerInventory.application.usescases.RouterMgmtUseCase;
import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.exception.RouterException;
import com.telefonica.hispam.routerInventory.util.Util;

import org.eclipse.microprofile.metrics.annotation.Counted;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/routers")
public class HttpInputAdapter {
    
    private static final Logger LOG = Logger.getLogger(HttpInputAdapter.class);

    @Inject
    RouterMgmtUseCase routerMgmtUseCase;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(description = "How many routers return", name = "numberCounted")
    public Response addRouter(Router router) {
        try {
            LOG.info("Add router: " + Util.toJson(router));
            var added = routerMgmtUseCase.addRouter(router);
            routerMgmtUseCase.persistRouter(added);
            LOG.info("Router added: " + Util.toJson(added));
            
            return  Response.status(Response.Status.CREATED).entity(router).build();
        } catch (RouterException e) {
            LOG.error("Router not added: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e.toJson()).build();
        } catch (Exception e) {
            LOG.error("Router not added: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e).build();
        }
    }
    
}

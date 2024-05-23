package com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb;

import java.util.List;

import com.telefonica.hispam.routerInventory.application.ports.output.RouterMgmtOutputPort;
import com.telefonica.hispam.routerInventory.domain.entity.Router;
import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.mappers.PanacheMongoDBMapper;
import com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.repository.PanacheMongoDBRepository;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@LookupIfProperty(name = "service.implementation-name", stringValue = "panache-mongodb")
public class PanacheMongoDBOutputAdapter implements RouterMgmtOutputPort {
 
    @Inject
    PanacheMongoDBRepository repository;

    @Override
    public Router persistRouter(Router router) {
        var doc = PanacheMongoDBMapper.toDocument(router);
        if(doc != null) repository.persist(doc);
        return router;
    }
    
    @Override
    public void retireRouter(Id id) {
        repository.deleteByUuid(id);
    }

    @Override
    public Router retrieveRouter(Id id) {
        var doc = repository.findByUuid(id);
        return PanacheMongoDBMapper.toDomain(doc);
    }

    @Override
    public  boolean existsRouter(Id id) {
        return repository.existsRouterByUuid(id);
    }

    @Override
    public List<Router> listRouters() {
        throw new UnsupportedOperationException(
            "Unimplemented method 'listRouters'");
    }
}

package com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.repository;

import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.data.RouterDocument;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheMongoDBRepository implements PanacheMongoRepository<RouterDocument> {
    
    public RouterDocument findByUuid(Id id){
        return find("uuid", uuid(id)).firstResult();
    }

    public void deleteByUuid(Id id){
       delete(find("uuid", uuid(id)).firstResult());
    }

    public boolean existsRouterByUuid(Id id){
        return findByUuid(id) != null;
    }

    private String uuid(Id id){
        if(id == null)
            throw new IllegalArgumentException("Id cannot be null") ;
        return id.getUuid().toString();
    }

}

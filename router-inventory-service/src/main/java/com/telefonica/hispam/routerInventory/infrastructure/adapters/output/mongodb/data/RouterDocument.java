package com.telefonica.hispam.routerInventory.infrastructure.adapters.output.mongodb.data;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="routers")
public class RouterDocument {
  
    public ObjectId id;

    public String uuid;

    public String name;
 
    public String address;
 
    public String city;
 
    public String state;
 
    public int zipCode;
 
    public String country;
  
    public float latitude;
  
    public float longitude;

    public String type;

    public String vendor;
}

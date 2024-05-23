package com.telefonica.hispam.routerInventory.domain.entity;

import com.telefonica.hispam.routerInventory.domain.vo.Id;
import com.telefonica.hispam.routerInventory.domain.vo.Location;
import com.telefonica.hispam.routerInventory.domain.vo.Type;
import com.telefonica.hispam.routerInventory.domain.vo.Vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;        

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder    
public class Router {

    @NonNull 
    private Id id;    

    private String name;
 
    private Location location;
  
    private Type type;

    private Vendor vendor;

}

package com.telefonica.hispam.networking.domain.entity;

import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Type;
import com.telefonica.hispam.networking.domain.vo.Vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;        

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder    
public class Router implements Device {

    private Id id;    

    private String name;
 
    private Location location;
  
    private Type type;

    private Vendor vendor;

}

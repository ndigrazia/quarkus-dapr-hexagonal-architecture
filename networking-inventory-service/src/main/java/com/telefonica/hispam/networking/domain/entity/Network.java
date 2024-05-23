package com.telefonica.hispam.networking.domain.entity;

import java.util.List;

import com.telefonica.hispam.networking.domain.vo.Id;
import com.telefonica.hispam.networking.domain.vo.Location;
import com.telefonica.hispam.networking.domain.vo.Topology;

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
public class Network {

    @NonNull 
    private Id id;    

    private String name;
 
    private Topology topology;

    private List<Device> devices;

    private Location location;
    
}

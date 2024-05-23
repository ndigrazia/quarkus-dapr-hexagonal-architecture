package com.telefonica.hispam.networking.domain.vo;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
//@AllArgsConstructor
@Builder
@Setter
public class Location {

    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String country;

    private float latitude;
    private float longitude;
    
}
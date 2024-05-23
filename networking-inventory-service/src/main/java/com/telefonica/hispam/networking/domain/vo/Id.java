package com.telefonica.hispam.networking.domain.vo;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Id {

    private UUID uuid;

    private Id(UUID uuid){
        this.uuid = uuid;
    }

    public static Id generateWith(String id){
        return new Id(UUID.fromString(id));
    }

    public static Id generate(){
        return new Id(UUID.randomUUID());
    }
    
}
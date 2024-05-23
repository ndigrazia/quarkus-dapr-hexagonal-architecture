package com.telefonica.hispam.routerInventory.util;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    
    private static final Logger LOG = Logger.getLogger(Util.class);

    public static String toJson(Object o) {
        try {
           ObjectMapper objectMapper = new ObjectMapper();
           String json = objectMapper.writeValueAsString(o);
           if (LOG.isDebugEnabled()) LOG.debug("toJson: " + json);
           return json;
        } catch (JsonProcessingException e) {
            LOG.error("toJson: " + e.getMessage());
            return "{}";
        }
    }
    
}

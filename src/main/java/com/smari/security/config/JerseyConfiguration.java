package com.smari.security.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/rest/v1")
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        packages("com.smari.security.controller");
    }
}

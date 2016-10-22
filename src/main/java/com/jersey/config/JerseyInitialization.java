package com.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.walmart.hackathon.resources.BucketListResource;
import com.walmart.hackathon.resources.GroupResource;
import com.walmart.hackathon.resources.ItemResource;
import com.walmart.hackathon.resources.UserResource;

public class JerseyInitialization extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public JerseyInitialization() {
        this.register(new JacksonJsonProvider(ObjectMapperFactory.create()));
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        //this.packages(true, "com.jersey.resources");
        this.packages(true, "com.walmart.hackathon.resources");
        //this.register(UserResource.class);
        //this.register(GroupResource.class);
        //this.register(BucketListResource.class);
       // this.register(ItemResource.class);
    }
}

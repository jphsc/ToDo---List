package br.com.rhsc.configuration;

import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

/* configura e mapeia os recursos rest*/

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("br.com.rhsc.api"); // lista de pacotes que possuem resources
        register(JsonBindingFeature.class);
//        property(JsonFeature.FEATURE_POJO_MAPPING, true);
//        property(JacksonFeature.SUPPORTS_ACCEPT_HEADER, true);
    }
}


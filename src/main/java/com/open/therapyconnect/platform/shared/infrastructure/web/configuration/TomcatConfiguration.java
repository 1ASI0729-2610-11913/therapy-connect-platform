package com.open.therapyconnect.platform.shared.infrastructure.web.configuration;

import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.tomcat.TomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration {

    @Bean
    public WebServerFactoryCustomizer<TomcatWebServerFactory> tomcatNio2Customizer() {
        return factory -> factory.setProtocol(Http11Nio2Protocol.class.getName());
    }
}

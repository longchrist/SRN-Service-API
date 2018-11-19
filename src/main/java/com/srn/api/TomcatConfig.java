package com.srn.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class TomcatConfig  implements EmbeddedServletContainerCustomizer {

    @Value("${app.tomcat.port}")
    int port;

    @Value("${app.tomcat.uri.encoding}")
    String encoding;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory)container;
        tomcat.setUriEncoding(Charset.forName(encoding));
        tomcat.setPort(port);
    }
}
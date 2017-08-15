package org.vaadin.addons.excelexporter.demo.config;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration for JPA layer.
 */
@Configuration
public class TomcatConfig {

    /**
     * Returns TomcatEmbeddedServletContainerFactory.
     *
     * @return the TomcatEmbeddedServletContainerFactory
     */
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatFactory () {
        return new TomcatEmbeddedServletContainerFactory () {

            @Override
            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer ( Tomcat tomcat ) {
                tomcat.enableNaming ();
                TomcatEmbeddedServletContainer container = super.getTomcatEmbeddedServletContainer ( tomcat );
                for ( Container child: container.getTomcat ().getHost ().findChildren () ) {
                    if ( child instanceof Context ) {
                        ClassLoader contextClassLoader = ( (Context) child ).getLoader ().getClassLoader ();
                        Thread.currentThread ().setContextClassLoader ( contextClassLoader );
                        break;
                    }
                }
                return container;
            }
        };
    }

}

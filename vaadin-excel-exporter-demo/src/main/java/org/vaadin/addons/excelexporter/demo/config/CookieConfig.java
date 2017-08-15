package org.vaadin.addons.excelexporter.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Cookie Configuration.
 */
@Configuration
public class CookieConfig {

    /**
     * SessionTrackingConfigListener.
     *
     * @return a new instance
     */
    @Bean
    public SessionTrackingConfigListener sessionTrackingConfigListener () {
        return new SessionTrackingConfigListener ();
    }

}

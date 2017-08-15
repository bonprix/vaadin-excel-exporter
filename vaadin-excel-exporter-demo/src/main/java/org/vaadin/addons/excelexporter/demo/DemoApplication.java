package org.vaadin.addons.excelexporter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * Spring Boot Application.
 */
@SpringBootApplication
@ServletComponentScan
public class DemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder application ) {
        return application.sources ( DemoApplication.class );
    }

    /**
     * The main.
     *
     * @param args to use
     */
    public static void main ( String [] args ) {
        SpringApplication.run ( DemoApplication.class, args );
    }
}

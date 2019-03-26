package com.hubin;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableCaching
@ServletComponentScan
@Configuration
public class App 
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(App.class).run(args);
    }

}

package com.listen.sspg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SspgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SspgApplication.class, args);
    }

}

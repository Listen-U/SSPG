package com.listen.sspg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.listen.sspg.dao")
public class SspgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SspgApplication.class, args);
    }

}

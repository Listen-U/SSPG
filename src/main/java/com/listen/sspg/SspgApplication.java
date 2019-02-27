package com.listen.sspg;

import java.util.Properties;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Listen
 * @date 2019/2/26
 */
@SpringBootApplication
@MapperScan(value="com.listen.sspg.dao")
public class SspgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SspgApplication.class, args);
    }

}

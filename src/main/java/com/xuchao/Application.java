package com.xuchao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.xuchao.mapper")//扫描dao包
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

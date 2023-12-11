package com.example.ebookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EBookServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EBookServerApplication.class, args);
        //System.out.printf(MD5Util.encode("easybook123"));
    }

}

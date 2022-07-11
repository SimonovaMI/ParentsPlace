package com.smi.parents_place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ParentsPlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParentsPlaceApplication.class, args);
    }

}

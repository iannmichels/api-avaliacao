package com.example.vaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(
        basePackageClasses = {VagaApplication.class}
)
@SpringBootApplication
public class VagaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VagaApplication.class, args);
    }

}

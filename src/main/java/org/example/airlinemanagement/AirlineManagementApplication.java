package org.example.airlinemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AirlineManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineManagementApplication.class, args);
    }

}

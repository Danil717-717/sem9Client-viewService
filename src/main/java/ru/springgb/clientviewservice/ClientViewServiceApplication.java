package ru.springgb.clientviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.springgb.clienttask","ru.springgb.clientexecutor"})
public class ClientViewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientViewServiceApplication.class, args);
    }

}

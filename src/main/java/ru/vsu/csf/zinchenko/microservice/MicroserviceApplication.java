package ru.vsu.csf.zinchenko.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationService;

@SpringBootApplication
public class MicroserviceApplication implements CommandLineRunner {

    @Autowired
    private VacationService service;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(service.calculateVacationMoney(34,12));
    }
}

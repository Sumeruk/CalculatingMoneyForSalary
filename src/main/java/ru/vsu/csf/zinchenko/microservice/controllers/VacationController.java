package ru.vsu.csf.zinchenko.microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDaysDTO;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationService;

import java.text.ParseException;

@RestController
public class VacationController {

    private VacationService vacationService;

    @Autowired
    public void setVacationService(VacationService vacationService){
        this.vacationService = vacationService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Float> getSumOfVacationMoney(@RequestBody SalaryAndDaysDTO salaryAndDays){

        try {
            float sumOfVacationMoney = vacationService.calculateVacationMoney(salaryAndDays);
            return ResponseEntity.ok(sumOfVacationMoney);

        } catch (ParseException pe){
            return ResponseEntity.badRequest().build();
        }

    }
}

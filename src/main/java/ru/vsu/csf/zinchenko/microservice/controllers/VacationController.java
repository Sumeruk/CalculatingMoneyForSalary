package ru.vsu.csf.zinchenko.microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.zinchenko.microservice.DTO.ErrorDTO;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@RestController
public class VacationController {

    private VacationService vacationService;

    @Autowired
    public void setVacationService(VacationService vacationService){
        this.vacationService = vacationService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Object> getSumOfVacationMoney(@RequestBody SalaryAndDatesDTO salaryAndDays){

        try {
            float sumOfVacationMoney = vacationService.calculateVacationMoney(salaryAndDays);
            return ResponseEntity.ok(sumOfVacationMoney);

        } catch (ParseException parseException){
            ErrorDTO errMessage = new ErrorDTO(new Date().toString(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.toString(), "/calculate",
                    "Wrong date format. Required: dd-mm-yyyy");
            return ResponseEntity.badRequest().body(errMessage);
        } catch (IOException io){
            ErrorDTO errMessage = new ErrorDTO(new Date().toString(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.toString(), "/calculate",
                    "Wrong date ratio: the first date is the beginning of the vacation, " +
                            "the second date is the end of the vacation");
            return ResponseEntity.badRequest().body(errMessage);
        }

    }
}

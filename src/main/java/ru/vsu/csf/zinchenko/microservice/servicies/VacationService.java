package ru.vsu.csf.zinchenko.microservice.servicies;

import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;

import java.io.IOException;
import java.text.ParseException;

public interface VacationService {
    float calculateVacationMoney(float averageSalary, int vacationDays);
    float calculateVacationMoney(SalaryAndDatesDTO salaryAndDays) throws ParseException, IOException;
}

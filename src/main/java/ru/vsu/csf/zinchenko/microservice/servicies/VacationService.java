package ru.vsu.csf.zinchenko.microservice.servicies;

import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDaysDTO;

import java.text.ParseException;

public interface VacationService {
    float calculateVacationMoney(float averageSalary, int vacationDays);
    float calculateVacationMoney(SalaryAndDaysDTO salaryAndDays) throws ParseException;
}

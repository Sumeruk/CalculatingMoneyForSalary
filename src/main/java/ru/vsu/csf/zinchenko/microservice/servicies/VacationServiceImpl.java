package ru.vsu.csf.zinchenko.microservice.servicies;

import org.springframework.stereotype.Service;

@Service
public class VacationServiceImpl implements VacationService {

    @Override
    public float calculateVacationMoney(float averageSalary, int vacationDays) {
        float salaryForDay = calculatingSalaryForDay(averageSalary);
        return salaryForDay * vacationDays;
    }

    private float calculatingSalaryForDay(float averageSalary){
        return averageSalary / 21;
    }

}

package ru.vsu.csf.zinchenko.microservice.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.csf.zinchenko.microservice.config.ReaderConfiguration;

@Service
public class VacationServiceImpl implements VacationService {

    private ReaderConfiguration config;

    @Autowired
    public void setConfig(ReaderConfiguration config) {
        this.config = config;
    }

    @Override
    public float calculateVacationMoney(float averageSalary, int vacationDays) {
        float salaryForDay = calculatingSalaryForDay(averageSalary);
        return salaryForDay * vacationDays;
    }

    private float calculatingSalaryForDay(float averageSalary){
        return averageSalary / config.getAvgDaysInMonth();
    }

}

package ru.vsu.csf.zinchenko.microservice.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;
import ru.vsu.csf.zinchenko.microservice.config.ReaderConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class VacationServiceImpl implements VacationService {

    private ReaderConfiguration config;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    public void setConfig(ReaderConfiguration config) {
        this.config = config;
    }

    @Override
    public float calculateVacationMoney(float averageSalary, int vacationDays) {
        float salaryForDay = getSalaryForDay(averageSalary);
        return salaryForDay * vacationDays;
    }

    @Override
    public float calculateVacationMoney(SalaryAndDatesDTO salaryAndDays) throws ParseException {
        float salaryForDay = getSalaryForDay(salaryAndDays.getAverageSalary());

        return salaryForDay * getAmountVacationDays(salaryAndDays.getStartDate(),
                salaryAndDays.getEndDate());
    }

    private long getAmountVacationDays(String startDay, String endDay) throws ParseException {
        Date startDate = parseStringToDate(startDay);
        Date endDate = parseStringToDate(endDay);

        long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());

        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private Date parseStringToDate(String startDay) throws ParseException {
        return dateFormat.parse(startDay);
    }

    private float getSalaryForDay(float averageSalary){
        return averageSalary / config.getAvgDaysInMonth();
    }
}

package ru.vsu.csf.zinchenko.microservice.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;
import ru.vsu.csf.zinchenko.microservice.config.ReaderConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
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
        return roundToTwoDecimalForFloat(salaryForDay * vacationDays);
    }

    @Override
    public float calculateVacationMoney(SalaryAndDatesDTO salaryAndDays) throws ParseException, IOException {
        float salaryForDay = getSalaryForDay(salaryAndDays.getAverageSalary());
        long vacationDays = getAmountVacationDays(salaryAndDays.getStartDate(),
                salaryAndDays.getEndDate());
        if (vacationDays < 0){
            throw new IOException();
        }
        return roundToTwoDecimalForFloat(salaryForDay * vacationDays);
    }

    private long getAmountVacationDays(String startDay, String endDay) throws ParseException {
        Date startDate = parseStringToDate(startDay);
        Date endDate = parseStringToDate(endDay);

        long diffInMillies = startDate.getTime() - endDate.getTime();

        if (diffInMillies > 0){
            return -1;
        }

        return TimeUnit.DAYS.convert(Math.abs(diffInMillies), TimeUnit.MILLISECONDS);
    }

    private Date parseStringToDate(String startDay) throws ParseException {
        return dateFormat.parse(startDay);
    }

    private float getSalaryForDay(float averageSalary){
        return averageSalary / config.getAvgDaysInMonth();
    }

    private float roundToTwoDecimalForFloat(float number){
        BigDecimal a = new BigDecimal(Math.round(number * 100));
        a = a.movePointLeft(2);
        return a.floatValue();
    }

    public VacationServiceImpl(ReaderConfiguration config) {
        this.config = config;
        dateFormat.setLenient(false);
    }

    public VacationServiceImpl() {
        dateFormat.setLenient(false);
    }
}

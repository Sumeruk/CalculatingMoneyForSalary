package ru.vsu.csf.zinchenko.microservice.DTO;

import java.text.DateFormat;

public class SalaryAndDaysDTO {
    private float averageSalary;
    private int vacationDays;

    private String startDate;

    private String endDate;

    public SalaryAndDaysDTO(float averageSalary, int vacationDays, String startDate, String endDate) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public float getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(float averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

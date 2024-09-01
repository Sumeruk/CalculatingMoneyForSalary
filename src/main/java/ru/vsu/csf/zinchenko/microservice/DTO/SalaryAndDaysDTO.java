package ru.vsu.csf.zinchenko.microservice.DTO;

public class SalaryAndDaysDTO {
    private float averageSalary;
    private int vacationDays;

    public SalaryAndDaysDTO(float averageSalary, int vacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
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
}

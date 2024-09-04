package ru.vsu.csf.zinchenko.microservice.DTO;

public class SalaryAndDatesDTO {
    private float averageSalary;
    private String startDate;
    private String endDate;

    public SalaryAndDatesDTO(float averageSalary, String startDate, String endDate) {
        this.averageSalary = averageSalary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SalaryAndDatesDTO() {

    }

    public float getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(float averageSalary) {
        this.averageSalary = averageSalary;
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

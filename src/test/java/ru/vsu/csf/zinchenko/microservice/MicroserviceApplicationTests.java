package ru.vsu.csf.zinchenko.microservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;
import ru.vsu.csf.zinchenko.microservice.config.ReaderConfiguration;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationService;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationServiceImpl;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.math.BigDecimal;
import java.text.ParseException;

@SpringBootTest
class MicroserviceApplicationTests {

    private VacationService vacationService;
    @BeforeEach
    public void setVacationService(@Autowired VacationServiceImpl service) {
        this.vacationService = service;
    }

    @Test
    void testCalculatingMoneyForSalary(){
        SalaryAndDatesDTO salaryAndDates = Mockito.mock(SalaryAndDatesDTO.class);
        Mockito.when(salaryAndDates.getAverageSalary()).thenReturn(50000f);
        Mockito.when(salaryAndDates.getStartDate()).thenReturn("10-12-2024");
        Mockito.when(salaryAndDates.getEndDate()).thenReturn("20-10-2024");

        try {
            float result = vacationService.calculateVacationMoney(salaryAndDates);
            Assertions.assertEquals(17064.85f, result);
        } catch (IOException | ParseException io){

        }

    }

    @Test
    void testWrongDateFormatForCalculatingMoneyForSalary(){
        ReaderConfiguration reader = Mockito.mock(ReaderConfiguration.class);
        Mockito.when(reader.getAvgDaysInMonth()).thenReturn(29.3f);

        SalaryAndDatesDTO salaryAndDates = new SalaryAndDatesDTO(
                50000f, "110-2024", "20-10-2024");

        Assertions.assertThrows(ParseException.class,
                () -> {vacationService.calculateVacationMoney(salaryAndDates);});
    }

    @Test
    void testWrongInputDateForCalculatingMoneyForSalary(){
        ReaderConfiguration reader = Mockito.mock(ReaderConfiguration.class);
        Mockito.when(reader.getAvgDaysInMonth()).thenReturn(29.3f);

        SalaryAndDatesDTO salaryAndDates = new SalaryAndDatesDTO(
                50000f, "22-10-2024", "20-10-2024");

        Assertions.assertThrows(IOException.class,
                () -> {vacationService.calculateVacationMoney(salaryAndDates);});
    }

}

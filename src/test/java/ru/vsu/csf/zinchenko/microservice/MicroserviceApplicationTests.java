package ru.vsu.csf.zinchenko.microservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsu.csf.zinchenko.microservice.DTO.SalaryAndDatesDTO;
import ru.vsu.csf.zinchenko.microservice.config.ReaderConfiguration;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationService;
import ru.vsu.csf.zinchenko.microservice.servicies.VacationServiceImpl;

import java.io.IOException;
import java.text.ParseException;

@SpringBootTest
class MicroserviceApplicationTests {

    private VacationService vacationService;
    @BeforeEach
    public void setVacationService(@Autowired VacationServiceImpl service) {
        this.vacationService = service;
    }

    @ParameterizedTest
    @CsvSource({
            "17064.85, 50000, 10-10-2024, 20-10-2024",
            "14625.26, 42852, 31-12-2024, 10-1-2025",
            "0, 0, 31-12-2024, 10-1-2025"
    })
    @DisplayName(value = "normal parameters")
    void testCalculatingMoneyForSalary(float expected,
                                       float avgSalary,
                                       String startDate,
                                       String endDate){
        SalaryAndDatesDTO salaryAndDates = Mockito.mock(SalaryAndDatesDTO.class);
        Mockito.when(salaryAndDates.getAverageSalary()).thenReturn(avgSalary);
        Mockito.when(salaryAndDates.getStartDate()).thenReturn(startDate);
        Mockito.when(salaryAndDates.getEndDate()).thenReturn(endDate);

        try {
            float result = vacationService.calculateVacationMoney(salaryAndDates);
            Assertions.assertEquals(expected, result);
        } catch (IOException | ParseException ignored){}

    }

    @Test
    @DisplayName(value = "wrong date format")
    void testWrongDateFormatForCalculatingMoneyForSalary(){
        ReaderConfiguration reader = Mockito.mock(ReaderConfiguration.class);
        Mockito.when(reader.getAvgDaysInMonth()).thenReturn(29.3f);

        SalaryAndDatesDTO salaryAndDates = new SalaryAndDatesDTO(
                50000f, "2024-12-10", "20-10-2024");

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

package ru.vsu.csf.zinchenko.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ReaderConfiguration {
    private int avgDaysInMonth;

    public int getAvgDaysInMonth() {
        return avgDaysInMonth;
    }

    public void setAvgDaysInMonth(int avgDaysInMonth) {
        this.avgDaysInMonth = avgDaysInMonth;
    }
}

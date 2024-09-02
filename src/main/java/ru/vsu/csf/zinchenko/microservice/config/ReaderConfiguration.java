package ru.vsu.csf.zinchenko.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ReaderConfiguration {
    private float avgDaysInMonth;

    public float getAvgDaysInMonth() {
        return avgDaysInMonth;
    }

    public void setAvgDaysInMonth(float avgDaysInMonth) {
        this.avgDaysInMonth = avgDaysInMonth;
    }
}

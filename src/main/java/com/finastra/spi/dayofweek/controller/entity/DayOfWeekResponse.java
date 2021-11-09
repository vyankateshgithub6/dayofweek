package com.finastra.spi.dayofweek.controller.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayOfWeekResponse {
    private String date;
    private String dayOfWeek;

    public DayOfWeekResponse(String date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatters);
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        this.date = date;
        this.dayOfWeek = dayOfWeek.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "date= \"" + date + '\"' +
                ", dayOfWeek=\"" + dayOfWeek + '\"' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}

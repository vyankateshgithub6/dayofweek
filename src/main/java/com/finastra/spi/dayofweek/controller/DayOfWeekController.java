package com.finastra.spi.dayofweek.controller;

import com.finastra.spi.dayofweek.controller.entity.DayOfWeekResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class DayOfWeekController {

    Logger logger = LoggerFactory.getLogger(DayOfWeekController.class);

    @PostMapping("/day-of-week")
    @ResponseBody
    public ResponseEntity<Object> getDayOfWeek(@RequestBody Map<String, String> inputDate) {
        return new ResponseEntity<>(new DayOfWeekResponse(inputDate.get("date")), HttpStatus.OK);
    }
}

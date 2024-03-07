package com.attendy.attendy.controller;

import com.attendy.attendy.entity.Attendance;
import com.attendy.attendy.service.interfaces.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/student/{id}")
    public List<Attendance> getStudentAttendance(@PathVariable Long id,
            @RequestParam(required = true) String duration) {
        return statsService.getStudentAttendance(id, duration);
    }

    @GetMapping("/teacher/{stream}")
    public List<Attendance> getTeacherAttendance(@PathVariable String stream,
            @RequestParam(required = true) String duration) {
        return statsService.getTeacherAttendance(stream, duration);
    }
}
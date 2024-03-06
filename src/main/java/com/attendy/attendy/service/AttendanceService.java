package com.attendy.attendy.service;

import com.attendy.attendy.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendance();
    Attendance getAttendanceById(Long id);
    Attendance saveAttendance(Attendance attendance);
    void deleteAttendance(Long id);
    List<Attendance> getAttendanceByStudentId(Long studentId);
}

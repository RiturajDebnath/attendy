package com.attendy.attendy.service.interfaces;

import com.attendy.attendy.entity.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendance();
    Attendance getAttendanceById(Long id);
    Attendance saveAttendance(Attendance attendance);
    void deleteAttendance(Long id);
    List<Attendance> getAttendanceByStudentId(Long studentId);
    List<Attendance> getAttendanceByStudentIdAndDuration(Long studentId, Date startDate, Date endDate);
    List<Attendance> getAttendanceByStreamAndDuration(String stream, Date startDate, Date endDate);
}

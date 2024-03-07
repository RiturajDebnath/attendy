package com.attendy.attendy.service.interfaces;

import com.attendy.attendy.entity.Attendance;
import java.util.List;

public interface StatsService {
    List<Attendance> getStudentAttendance(Long id, String duration);
    List<Attendance> getTeacherAttendance(String stream, String duration);
}

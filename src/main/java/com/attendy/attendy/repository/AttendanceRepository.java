package com.attendy.attendy.repository;

import com.attendy.attendy.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> getAttendanceByStudentId(Long studentId);
    List<Attendance> getAttendanceByStudentIdAndDateBetween(Long studentId, Date startDate, Date endDate);
    List<Attendance> getAttendanceByStreamAndDateBetween(String stream, Date startDate, Date endDate);
}

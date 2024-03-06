package com.attendy.attendy.repository;

import com.attendy.attendy.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> getAttendanceByStudentId(Long studentId);
}

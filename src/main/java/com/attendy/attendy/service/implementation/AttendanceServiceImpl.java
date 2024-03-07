package com.attendy.attendy.service.implementation;

import com.attendy.attendy.entity.Attendance;
import com.attendy.attendy.repository.AttendanceRepository;
import com.attendy.attendy.service.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.getAttendanceByStudentId(studentId);
    }

    @Override
    public List<Attendance> getAttendanceByStudentIdAndDuration(Long studentId, Date startDate, Date endDate) {
        return attendanceRepository.getAttendanceByStudentIdAndDateBetween(studentId, startDate, endDate);
    }

    @Override
    public List<Attendance> getAttendanceByStreamAndDuration(String stream, Date startDate, Date endDate) {
        return attendanceRepository.getAttendanceByStreamAndDateBetween(stream, startDate, endDate);
    }
}

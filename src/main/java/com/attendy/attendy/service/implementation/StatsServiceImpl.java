package com.attendy.attendy.service.implementation;

import com.attendy.attendy.entity.Attendance;
import com.attendy.attendy.repository.AttendanceRepository;
import com.attendy.attendy.service.interfaces.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private Date[] calculateDateRange(String duration) {
        Date endDate = new Date(); // Current date
        Date startDate;

        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        switch (duration) {
            case "week":
                startDate = java.sql.Date.valueOf(localEndDate.minusDays(7));
                break;
            case "month":
                startDate = java.sql.Date.valueOf(localEndDate.minusMonths(1));
                break;
            default:
                // If duration is neither "week" nor "month", return empty list or throw exception as required
                return new Date[0];
        }

        return new Date[]{startDate, endDate};
    }

    public List<Attendance> getStudentAttendance(Long id, String duration){
        Date[] dateRange = calculateDateRange(duration);
        return attendanceRepository.getAttendanceByStudentIdAndDateBetween(id, dateRange[0], dateRange[1]);
    };
    public List<Attendance> getTeacherAttendance(String stream, String duration){
        Date[] dateRange = calculateDateRange(duration);
        return attendanceRepository.getAttendanceByStreamAndDateBetween(stream, dateRange[0], dateRange[1]);
    };
}
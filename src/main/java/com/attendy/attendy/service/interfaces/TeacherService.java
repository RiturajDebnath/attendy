package com.attendy.attendy.service.interfaces;

import com.attendy.attendy.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeacher();
    Teacher getTeacherById(Long id);
    Teacher saveTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    Teacher findByEmail(String email);
}

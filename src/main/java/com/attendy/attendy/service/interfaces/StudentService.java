package com.attendy.attendy.service.interfaces;

import com.attendy.attendy.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    Student findByEmail(String email);
}

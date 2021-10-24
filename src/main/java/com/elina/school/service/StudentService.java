package com.elina.school.service;

import com.elina.school.model.Status;
import com.elina.school.model.Student;

import java.util.List;

public interface StudentService {
    void save(Student student); //first and last name
    Student findById(Long student_id);
    List<Student> findAll();
    void addAptitudes(List<String> aptitude_names, Long student_id);
    void deleteAptitudes(List<String> aptitude_names, Long student_id);
    void deleteById(Long student_id);
}

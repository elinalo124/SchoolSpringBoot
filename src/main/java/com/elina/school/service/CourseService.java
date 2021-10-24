package com.elina.school.service;

import com.elina.school.model.Aptitude;
import com.elina.school.model.Course;
import com.elina.school.model.Status;

import java.util.List;

public interface CourseService {
    void save(Course course);
    Course findById(Long id);
    List<Course> findAll();
    void deleteById(Long id);
    Course updateById(Course newCourse, Long id);
    void setAptitudes(List<String> aptitude_names, Long id);
    void setStatus(String status_string, Long id);
}

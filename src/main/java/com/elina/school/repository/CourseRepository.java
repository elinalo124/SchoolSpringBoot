package com.elina.school.repository;

import com.elina.school.model.Course;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepository extends CrudRepository<Course, Long> {
}

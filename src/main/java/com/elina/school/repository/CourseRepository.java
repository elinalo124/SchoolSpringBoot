package com.elina.school.repository;

import com.elina.school.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;


public interface CourseRepository extends JpaRepository<Course, Long> {

}

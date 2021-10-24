package com.elina.school.repository;

import com.elina.school.model.Course;
import com.elina.school.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}

package com.elina.school.repository;

import com.elina.school.model.Course;
import com.elina.school.model.Enrollment;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
}

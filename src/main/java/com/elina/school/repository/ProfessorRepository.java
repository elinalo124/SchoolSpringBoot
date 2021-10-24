package com.elina.school.repository;

import com.elina.school.model.Course;
import com.elina.school.model.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}

package com.elina.school.repository;

import com.elina.school.model.Aptitude;
import com.elina.school.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface AptitudeRepository extends CrudRepository<Aptitude, Long> {
    Aptitude findByName(String name);
}

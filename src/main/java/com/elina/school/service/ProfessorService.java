package com.elina.school.service;

import com.elina.school.model.Professor;
import com.elina.school.model.Status;

import java.util.List;

public interface ProfessorService {
    void save(Professor professor); //first and last name
    Professor findById(Long professor_id);
    List<Professor> findAll();
    void addAptitudes(List<String> aptitude_names, Long professor_id);
    void deleteAptitudes(List<String> aptitude_names, Long professor_id);
    void deleteById(Long professor_id);
}

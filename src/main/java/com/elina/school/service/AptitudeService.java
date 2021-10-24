package com.elina.school.service;

import com.elina.school.model.Aptitude;

import java.util.List;

public interface AptitudeService {
    void save(Aptitude aptitude);
    Aptitude findById(Long id);
    List<Aptitude> findAll();
    void deleteById(Long id);
}

package com.elina.school.service.serviceImpl;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Aptitude;
import com.elina.school.model.Professor;
import com.elina.school.repository.AptitudeRepository;
import com.elina.school.repository.ProfessorRepository;
import com.elina.school.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
    private ProfessorRepository professorRepository;
    private AptitudeRepository aptitudeRepository;

    @Autowired
    public void setProfessorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @Autowired
    public void setAptitudeRepository(AptitudeRepository aptitudeRepository) {
        this.aptitudeRepository = aptitudeRepository;
    }

    @Override
    public void save(Professor professor) {//First name and last name.
        professorRepository.save(professor);
    }

    @Override
    public Professor findById(Long professor_id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professor_id);

        if(optionalProfessor.isPresent())
            return optionalProfessor.get();
        else
            throw new NotFoundException("Professor Not Found");
    }

    @Override
    public List<Professor> findAll() {
        return (List<Professor>) professorRepository.findAll();
    }

    @Override
    public void addAptitudes(List<String> aptitude_names, Long professor_id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professor_id);
        Professor professorToUpdate;
        List<Aptitude> aptitudesToAdd = new ArrayList<>();
        aptitude_names.forEach(aptitude_name -> aptitudesToAdd.add(aptitudeRepository.findByName(aptitude_name)));

        if (optionalProfessor.isPresent()) {
            professorToUpdate = optionalProfessor.get();
            professorToUpdate.setAptitudes(aptitudesToAdd);
            professorRepository.save(professorToUpdate);
            aptitudesToAdd.forEach(aptitude -> {
                Aptitude aptitudeToUpdate = aptitudeRepository.findByName(aptitude.getName());
                aptitudeToUpdate.getProfessors().add(professorToUpdate);
                aptitudeRepository.save(aptitudeToUpdate);
            });
        }
    }

    @Override
    public void deleteAptitudes(List<String> aptitude_names, Long professor_id){
            Optional<Professor> optionalProfessor = professorRepository.findById(professor_id);
            Professor professorToUpdate;
            List<Aptitude> aptitudesToRemove = new ArrayList<>();
            aptitude_names.forEach(aptitude_name -> aptitudesToRemove.add(aptitudeRepository.findByName(aptitude_name)));

            if (optionalProfessor.isPresent()) {
                professorToUpdate = optionalProfessor.get();
                aptitudesToRemove.forEach(aptitude -> {
                    Aptitude aptitudeToUpdate = aptitudeRepository.findByName(aptitude.getName());
                    aptitudeToUpdate.getProfessors().remove(professorToUpdate);
                    aptitudeRepository.save(aptitudeToUpdate);
                    professorToUpdate.getAptitudes().remove(aptitude);
                    professorRepository.save(professorToUpdate);
                });
            }
        }

    @Override
    public void deleteById(Long professor_id) {
        if(professorRepository.findById(professor_id).isPresent())
            professorRepository.deleteById(professor_id);
    }
}

package com.elina.school.controller;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Professor;
import com.elina.school.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }

    //CREATE 
    @PostMapping("")
    public void saveCourse(@RequestBody Professor newProfessor){
        System.out.println("Controller is saving:\n"+newProfessor);
        professorService.save(newProfessor);
    }

    //RETRIEVE
    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<Professor>(professorService.findById(id),
                    HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor Not Found");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List<Professor> professors = professorService.findAll();
        System.out.println("All professors\n"+professors);
        return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/add/{id}")
    public void addAptitudes(@RequestParam(name = "aptitude_names")List<String> aptitude_names, @PathVariable("id") Long professor_id){
        professorService.addAptitudes(aptitude_names, professor_id);
    }

    @PutMapping("/delete/{id}")
    public void deleteAptitudes(@RequestParam(name = "aptitude_names")List<String> aptitude_names, @PathVariable("id") Long professor_id){
        professorService.deleteAptitudes(aptitude_names, professor_id);
    }

    //DELETE
    @DeleteMapping("/{id}")
    void deleteProfessor(@PathVariable Long id) {
        professorService.deleteById(id);
    }
}

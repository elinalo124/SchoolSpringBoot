package com.elina.school.controller;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Enrollment;
import com.elina.school.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @Autowired
    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    //CREATE 
    @PostMapping("")
    public void saveEnrollment(@RequestParam(name = "enroll_date")LocalDateTime enroll_date, @RequestParam(name = "course_id") Long course_id){
        System.out.println("Controller is saving a new enrollment");
        enrollmentService.save(enroll_date, course_id);
    }

    //RETRIEVE
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollment(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<Enrollment>(enrollmentService.findById(id),
                    HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Not Found");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Enrollment>> getAllEnrollments(){
        List<Enrollment> enrollments = enrollmentService.findAll();
        System.out.println("All enrollments\n"+enrollments);
        return new ResponseEntity<List<Enrollment>>(enrollments, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/{id}")
    public void addStudents(@PathVariable("id") Long enrollment_id, @RequestParam(name = "student_ids")List<Long> student_ids){
        enrollmentService.addStudents(student_ids, enrollment_id);
    }

    @PutMapping("/{id}")
    public void addProfessor(@PathVariable("id") Long enrollment_id, @RequestParam(name = "professor_id")Long professor_id){
        enrollmentService.addProfessor(professor_id, enrollment_id);
    }

    //DELETE
    @DeleteMapping("/{id}")
    void deleteEnrollment(@PathVariable Long enrollment_id) {
        enrollmentService.deleteById(enrollment_id);
    }
}

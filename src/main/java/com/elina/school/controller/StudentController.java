package com.elina.school.controller;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Student;
import com.elina.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    //CREATE
    @PostMapping("")
    public void saveCourse(@RequestBody Student newStudent){
        System.out.println("Controller is saving:\n"+newStudent);
        studentService.save(newStudent);
    }

    //RETRIEVE
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<Student>(studentService.findById(id),
                    HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.findAll();
        System.out.println("All students\n"+students);
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/add/{id}")
    public void addAptitudes(@RequestParam(name = "aptitude_names")List<String> aptitude_names, @PathVariable("id") Long student_id){
        studentService.addAptitudes(aptitude_names, student_id);
    }

    @PutMapping("/delete/{id}")
    public void deleteAptitudes(@RequestParam(name = "aptitude_names")List<String> aptitude_names, @PathVariable("id") Long student_id){
        studentService.deleteAptitudes(aptitude_names, student_id);
    }

    //DELETE
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}

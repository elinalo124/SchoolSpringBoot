package com.elina.school.controller;


import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Course;
import com.elina.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    //CREATE 
    @PostMapping("")
    public void saveCourse(@RequestBody Course newCourse){
        System.out.println("Controller is saving:\n"+newCourse);
        courseService.save(newCourse);
    }

    //RETRIEVE
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<Course>(courseService.findById(id),
                    HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.findAll();
        System.out.println("All courses\n"+courses);
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }
    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return new ResponseEntity<>(courseService.updateById(newCourse, id), HttpStatus.OK);
    }

    @PutMapping("/aptitudes/{id}")
    public void addAptitudes(@RequestParam(name = "aptitude_names") List<String> aptitude_names, @PathVariable("id") Long id){
        courseService.addAptitudes(aptitude_names, id);
    }

    @PutMapping("/status/{id}")
    public void setStatus(@RequestParam String status, @PathVariable("id") Long id){
        courseService.setStatus(status, id);
    }

    //DELETE
    @DeleteMapping("/{id}")
    void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }

}

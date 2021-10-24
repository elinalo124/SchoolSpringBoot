package com.elina.school.controller;


import com.elina.school.model.Course;
import com.elina.school.service.serviceImpl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elina")
public class CourseController {

    private CourseServiceImpl courseServiceImpl;

    @Autowired
    public void setCourseServiceImpl(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    //CREATE 
    @PostMapping("/courses")
    public void saveCourse(@RequestBody Course newCourse){
        System.out.println("Controller is saving:\n"+newCourse);
        courseServiceImpl.save(newCourse);
    }

    //RETRIEVE
    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable("id") Long id){
        Course course = courseServiceImpl.findById(id);
        System.out.println("get Course\n"+ course);
        return course;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        List<Course> courses = courseServiceImpl.findAll();
        System.out.println("All courses\n"+courses);
        return courses;
    }
    //UPDATE
    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return courseServiceImpl.updateById(newCourse, id);
    }

    //DELETE
    @DeleteMapping("/courses/{id}")
    void deleteEmployee(@PathVariable Long id) {
        courseServiceImpl.deleteById(id);
    }

}

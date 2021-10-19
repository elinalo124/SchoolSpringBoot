package com.elina.school.service;

import com.elina.school.model.Course;
import com.elina.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

    private CourseRepository repository;

    public CourseServiceImpl() {
        System.out.println("Constructing CourseServiceImpl with no args");
    }

    @Autowired
    public void setRepository(CourseRepository repository) {
        this.repository = repository;
        System.out.println("Setting repo for CourseServiceImpl");
    }

    public void save(Course course){
        System.out.println("Service is saving:\n"+course);
        repository.save(course);
    }

    public Course findById(Long id){
        return repository.findById(id).get();
    }

    //public List<Course> findByName(String name){
    //    return repository.findByName(name);
    //}

    public List<Course> findAll(){
        return (List<Course>) repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Course updateById(Course newCourse, Long id) {
        return repository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setDescription(newCourse.getDescription());
                    course.setDepartment(newCourse.getDepartment());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });
    }

}

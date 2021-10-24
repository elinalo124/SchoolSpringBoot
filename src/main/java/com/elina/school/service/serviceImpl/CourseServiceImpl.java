package com.elina.school.service.serviceImpl;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Aptitude;
import com.elina.school.model.Course;
import com.elina.school.model.Status;
import com.elina.school.repository.AptitudeRepository;
import com.elina.school.repository.CourseRepository;
import com.elina.school.repository.StatusRespository;
import com.elina.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private StatusRespository statusRespository;
    private AptitudeRepository aptitudeRepository;
    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Autowired
    public void setStatusRespository(StatusRespository statusRespository) {
        this.statusRespository = statusRespository;
    }
    @Autowired
    public void setAptitudeRepository(AptitudeRepository aptitudeRepository) {
        this.aptitudeRepository = aptitudeRepository;
    }

    @Override
    public void save(Course course){
        courseRepository.save(course);
    }

    @Override
    public Course findById(Long id){
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent())
            return optionalCourse.get();
        else
            throw new NotFoundException("Course Not Found");
    }

    @Override
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long course_id){
        if(courseRepository.findById(course_id).isPresent())
            courseRepository.deleteById(course_id);

    }

    @Override
    public Course updateById(Course newCourse, Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourse_title(newCourse.getCourse_title());
                    course.setStart_date(newCourse.getStart_date());
                    course.setEnd_date(newCourse.getEnd_date());
                    course.setMin_grade(newCourse.getMin_grade());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }

    @Override
    public void setAptitudes(List<String> aptitude_names, Long id) {

        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course courseToUpdate;
        List<Aptitude> aptitudesToAdd = new ArrayList<>();
        aptitude_names.forEach(aptitude_name -> aptitudesToAdd.add(aptitudeRepository.findByName(aptitude_name)));

        if(optionalCourse.isPresent()){
            courseToUpdate = optionalCourse.get();
            courseToUpdate.setAptitudes(aptitudesToAdd);
            courseRepository.save(courseToUpdate);
            aptitudesToAdd.forEach(aptitude -> {
                Aptitude aptitudeToUpdate = aptitudeRepository.findByName(aptitude.getName());
                aptitudeToUpdate.getCourses().add(courseToUpdate);
                aptitudeRepository.save(aptitudeToUpdate);
            });
        }
        else
            throw new NotFoundException("Application Not Found");

    }

    @Override
    public void setStatus(String status_string, Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course courseToUpdate;
        Status statusToAdd = statusRespository.findByName(status_string);

        if(optionalCourse.isPresent()){
            courseToUpdate = optionalCourse.get();
            courseToUpdate.setStatus(statusToAdd);
            courseRepository.save(courseToUpdate);
            statusToAdd.getCourses().add(courseToUpdate);
            statusRespository.save(statusToAdd);
        }
        else
            throw new NotFoundException("Application Not Found");

    }

}

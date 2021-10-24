package com.elina.school.service.serviceImpl;

import com.elina.school.exception.NotFoundException;
import com.elina.school.model.Aptitude;
import com.elina.school.model.Student;
import com.elina.school.repository.AptitudeRepository;
import com.elina.school.repository.StudentRepository;
import com.elina.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private AptitudeRepository aptitudeRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Autowired
    public void setAptitudeRepository(AptitudeRepository aptitudeRepository) {
        this.aptitudeRepository = aptitudeRepository;
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(Long student_id) {
        Optional<Student> optionalStudent = studentRepository.findById(student_id);

        if(optionalStudent.isPresent())
            return optionalStudent.get();
        else
            throw new NotFoundException("Student Not Found");
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public void addAptitudes(List<String> aptitude_names, Long student_id) {
        Optional<Student> optionalStudent = studentRepository.findById(student_id);
        Student studentToUpdate;
        List<Aptitude> aptitudesToAdd = new ArrayList<>();
        aptitude_names.forEach(aptitude_name -> aptitudesToAdd.add(aptitudeRepository.findByName(aptitude_name)));

        if (optionalStudent.isPresent()) {
            studentToUpdate = optionalStudent.get();
            studentToUpdate.setAptitudes(aptitudesToAdd);
            studentRepository.save(studentToUpdate);
            aptitudesToAdd.forEach(aptitude -> {
                Aptitude aptitudeToUpdate = aptitudeRepository.findByName(aptitude.getName());
                aptitudeToUpdate.getStudents().add(studentToUpdate);
                aptitudeRepository.save(aptitudeToUpdate);
            });
        }
    }

    @Override
    public void deleteAptitudes(List<String> aptitude_names, Long student_id) {
        Optional<Student> optionalStudent = studentRepository.findById(student_id);
        Student studentToUpdate;
        List<Aptitude> aptitudesToRemove = new ArrayList<>();
        aptitude_names.forEach(aptitude_name -> aptitudesToRemove.add(aptitudeRepository.findByName(aptitude_name)));

        if (optionalStudent.isPresent()) {
            studentToUpdate = optionalStudent.get();
            aptitudesToRemove.forEach(aptitude -> {
                Aptitude aptitudeToUpdate = aptitudeRepository.findByName(aptitude.getName());
                aptitudeToUpdate.getStudents().remove(studentToUpdate);
                aptitudeRepository.save(aptitudeToUpdate);
                studentToUpdate.getAptitudes().remove(aptitude);
                studentRepository.save(studentToUpdate);
            });
        }
    }

    @Override
    public void deleteById(Long student_id) {
        if(studentRepository.findById(student_id).isPresent())
            studentRepository.deleteById(student_id);
    }
}

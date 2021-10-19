package com.elina.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course_title;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int min_grade;

    @ManyToOne
    private Status status;

    @ManyToMany
    private List<Aptitude> aptitudes;

    @OneToMany
    private List<Enrollment> enrollments;
    /*
    @ManyToOne
    @JoinColumn(name="department_id")
    @JsonBackReference
    private Department department;
     */

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_title='" + course_title + '\'' +
                '}';
    }
}

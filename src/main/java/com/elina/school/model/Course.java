package com.elina.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private Status status;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "COURSE_APTITUDES",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "aptitude_id"))
    private List<Aptitude> aptitudes;

    @OneToMany(mappedBy = "enrollment")
    private List<Enrollment> enrollments;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_title='" + course_title + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", min_grade=" + min_grade +
                ", status=" + status +
                ", aptitudes=" + aptitudes +
                ", enrollments=" + enrollments +
                '}';
    }
}

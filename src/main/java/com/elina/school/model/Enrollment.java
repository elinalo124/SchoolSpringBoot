package com.elina.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollment {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime enroll_date;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "ENROLLMENT_STUDENTS",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course")
    private Professor professor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course")
    private Course course;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enroll_date=" + enroll_date +
                ", students=" + students +
                ", professor=" + professor +
                ", course=" + course +
                '}';
    }
}

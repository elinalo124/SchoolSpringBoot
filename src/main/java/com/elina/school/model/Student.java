package com.elina.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;

    @ManyToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "STUDENT_APTITUDES",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "aptitude_id"))
    private List<Aptitude> aptitudes;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", enrollments=" + enrollments +
                ", aptitudes=" + aptitudes +
                '}';
    }
}

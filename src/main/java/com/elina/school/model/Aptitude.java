package com.elina.school.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aptitude {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aptitude_name;

    @ManyToMany
    private List<Course> courses;

    @ManyToMany
    private List<Professor> professors;

    @ManyToMany
    private List<Student> students;

}

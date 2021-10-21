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
public class Professor {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;

    @OneToMany(mappedBy = "professor")
    private List<Enrollment> enrollments;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "PRODESSOR_APTITUDES",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "aptitude_id"))
    private List<Aptitude> aptitudes;

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", enrollments=" + enrollments +
                ", aptitudes=" + aptitudes +
                '}';
    }
}

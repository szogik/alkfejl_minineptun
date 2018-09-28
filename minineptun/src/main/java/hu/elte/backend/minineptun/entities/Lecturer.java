package hu.elte.backend.minineptun.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    //this is the professor of these subjects
    @ManyToMany
    private List<Subject> subjects;


    //this is teaching at these courses
    @OneToMany
    private List<Course> courses;
}

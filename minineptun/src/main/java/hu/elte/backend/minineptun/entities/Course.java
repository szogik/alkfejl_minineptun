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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    @Column
    private String location;

    @Column
    private String time;

    //students who attending this class
    @ManyToMany
    private List<Student> students;

    //the course's subject
    @ManyToOne
    private Subject subject;

    //the lecturer who teaches
    @ManyToOne
    private Lecturer lecturer;


}

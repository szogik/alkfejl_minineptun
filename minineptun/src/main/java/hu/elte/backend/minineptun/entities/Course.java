package hu.elte.backend.minineptun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private CourseType type;

    @Column
    private String location;

    @Column
    private String time;

    //students attending this course
    @ManyToMany
    private List<Student> students;

    //the course's subject
    @ManyToOne
    private Subject subject;

    //the lecturer of the course
    @ManyToOne
    private Lecturer lecturer;

    public enum CourseType {
        LECTURE, PRACTICE
    }

}

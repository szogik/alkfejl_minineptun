package hu.elte.backend.minineptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "students")
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

    @ManyToMany
    @JsonIgnore
    private Set<Student> students;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    @JsonIgnore
    private Lecturer lecturer;

    public enum CourseType {
        LECTURE, PRACTICE
    }

}

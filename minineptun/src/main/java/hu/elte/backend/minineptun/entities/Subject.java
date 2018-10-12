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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Categories category;

    //professors of this subject
    @ManyToMany
    private List<Lecturer> lecturers;

    //courses of this subject
    @OneToMany
    private List<Course> courses;

    public enum Categories {
        COMPUTER_SCIENCE, NATURAL_SCIENCE, PROGRAMMING;
    }
}

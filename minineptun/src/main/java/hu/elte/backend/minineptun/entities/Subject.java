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
@EqualsAndHashCode(exclude = "lecturers")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Categories category;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Set<Lecturer> lecturers;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;

    public enum Categories {
        COMPUTER_SCIENCE, NATURAL_SCIENCE, PROGRAMMING;
    }
}

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
@EqualsAndHashCode
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    @EqualsAndHashCode.Include
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private Categories category;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<Lecturer> lecturers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Course> courses;

    public enum Categories {
        COMPUTER_SCIENCE, NATURAL_SCIENCE, PROGRAMMING;
    }
}

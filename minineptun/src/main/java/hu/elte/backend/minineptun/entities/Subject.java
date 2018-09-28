package hu.elte.backend.minineptun.entities;

import hu.elte.backend.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
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
    private Categories category;

    //professors of this subject
    @ManyToMany
    private List<Lecturer> lecturers;

    //courses of this subject
    @OneToMany
    private List<Course> courses;
}

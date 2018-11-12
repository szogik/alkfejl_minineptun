package hu.elte.backend.minineptun.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lecturer extends BaseEntity {

    //the lecturer is the professor of these subjects
    @ManyToMany
    private List<Subject> subjects;


    //the lecturer teaches at the following courses
    @OneToMany
    private List<Course> courses;
}

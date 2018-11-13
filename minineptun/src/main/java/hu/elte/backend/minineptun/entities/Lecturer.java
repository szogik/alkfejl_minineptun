package hu.elte.backend.minineptun.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lecturer extends BaseEntity {

    @ManyToMany
    private Set<Subject> subjects;

    @OneToMany
    @JsonIgnore
    private Set<Course> courses;
}

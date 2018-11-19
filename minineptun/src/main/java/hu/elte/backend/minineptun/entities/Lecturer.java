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
@EqualsAndHashCode(callSuper = true)
public class Lecturer extends BaseEntity {

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    @EqualsAndHashCode.Exclude
    private Set<Course> courses;
}

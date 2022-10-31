package DJ.models.identity.group.project;

import DJ.models.simple.benefit.Benefit;
import DJ.models.simple.characteristic.Characteristic;
import DJ.models.simple.skill.Skill;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long name;

    private String description;

    private int remuneration;

    @ElementCollection
    @CollectionTable(name = "benefits_project", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "benefits")
    private Set<Benefit> benefits = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills_project", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "skills")
    private Set<Skill> skills = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "characteristics_project", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "characteristics")
    private Set<Characteristic> characteristics = new HashSet<>();
}

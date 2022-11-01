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

    @Version
    private Long version;

    private Long name;

    private String description;

    private int remuneration;

    @ElementCollection
    @CollectionTable(name = "benefits", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> benefits = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> skills = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "characteristics", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> characteristics = new HashSet<>();
}

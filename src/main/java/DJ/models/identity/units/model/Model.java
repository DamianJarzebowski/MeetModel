package DJ.models.identity.units.model;

import DJ.models.identity.units.User;
import DJ.models.simple.achievement.Achievement;
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
public class Model extends User {

    @Id
    private Long id;

    private int age;

    @ElementCollection
    @CollectionTable(name = "characteristics_model", joinColumns = @JoinColumn(name = "model_id"))
    @Column(name = "characteristics")
    private Set<Characteristic> characteristics = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "achievements_model", joinColumns = @JoinColumn(name = "model_id"))
    @Column(name = "achievements")
    private Set<Achievement> achievements = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills_model", joinColumns = @JoinColumn(name = "model_id"))
    @Column(name = "skills")
    private Set<Skill> skills = new HashSet<>();
}

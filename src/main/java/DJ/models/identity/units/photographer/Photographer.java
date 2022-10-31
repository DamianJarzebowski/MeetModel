package DJ.models.identity.units.photographer;

import DJ.models.identity.units.User;
import DJ.models.simple.achievement.Achievement;
import DJ.models.simple.skill.Skill;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Photographer extends User {

    @Id
    private Long id;

    @ElementCollection
    @CollectionTable(name = "achievements_photographer", joinColumns = @JoinColumn(name = "photographer_id"))
    @Column(name = "achievements")
    private Set<Achievement> achievements = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills_photographer", joinColumns = @JoinColumn(name = "photographer_id"))
    @Column(name = "skills")
    private Set<Skill> skills = new HashSet<>();
}

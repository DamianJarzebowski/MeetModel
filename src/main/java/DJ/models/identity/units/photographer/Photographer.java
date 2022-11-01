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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "achievements", joinColumns = @JoinColumn(name = "photographer_id"))
    private Set<String> achievements = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "photographer_id"))
    private Set<String> skills = new HashSet<>();
}

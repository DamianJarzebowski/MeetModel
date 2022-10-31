package DJ.models.identity.units;

import DJ.models.simple.achievement.Achievement;
import DJ.models.simple.skill.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public abstract class User {

    private String name;

    private String lastName;

    private String description;

    private String email;

    private List<Achievement> achievements;

    private List<Skill> skills;

}

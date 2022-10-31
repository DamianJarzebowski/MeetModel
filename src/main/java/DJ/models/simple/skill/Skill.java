package DJ.models.simple.skill;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class Skill {

    @Id
    private Long id;

    private String name;
}

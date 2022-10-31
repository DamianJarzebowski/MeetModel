package DJ.models.identity.group.project;

import DJ.models.simple.benefit.Benefit;
import DJ.models.simple.characteristic.Characteristic;
import DJ.models.simple.skill.Skill;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import java.util.List;

@Data
@Accessors(chain = true)
@Embeddable
public class Project {

    private Long name;

    private String description;

    private int remuneration;

    private List<Benefit> benefits;

    private List<Skill> skills;

    private List<Characteristic> characteristics;


}

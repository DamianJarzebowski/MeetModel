package DJ.models.simple.achievement;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class Achievement {

    @Id
    private Long id;

    private String name;
}

package dj.models.identity.competition;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Accessors(chain = true)
@Embeddable
public class User {

    private String name;

    private String lastName;

    private String description;

    private int age;

    private String email;
}

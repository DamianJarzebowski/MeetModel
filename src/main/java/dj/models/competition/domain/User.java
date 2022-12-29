package dj.models.competition.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Accessors(chain = true)
@Embeddable
public class User {

    private String name;

    private String lastName;

    private String description;

    // With it enum experience is mapped in database as string and not number.
    @Enumerated(EnumType.STRING)
    private Experience experience;

    private String profession;

    private int age;

    private String email;

    public enum Experience {

        BEGINNER,
        SMALL,
        MEDIUM,
        BIG;

    }

}

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

    @Enumerated(EnumType.STRING)
    private Experience experience;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    private int age;

    private String email;

    public enum Experience {
        BEGINNER,
        SMALL,
        MEDIUM,
        BIG;
    }
    public enum Profession {
        MODEL,
        PHOTOGRAPHER;
    }

}

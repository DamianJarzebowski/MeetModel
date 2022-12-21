package dj.models.competition.domain;

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

    private String experience;

    private String profession;

    private int age;

    private String email;

}
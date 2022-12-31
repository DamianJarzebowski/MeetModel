package dj.models.competition.domain.dto;

import dj.models.competition.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private String name;

    private String lastName;

    private String description;

    private User.Experience experience;

    private User.Profession profession;

    private int age;

    private String email;
}

package dj.models.competition.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private String name;

    private String lastName;

    private String description;

    private String experience;

    private String profession;

    private int age;

    private String email;
}

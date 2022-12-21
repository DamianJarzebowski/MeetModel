package dj.models.competition.domain.dto;

import dj.models.competition.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private User user;

}

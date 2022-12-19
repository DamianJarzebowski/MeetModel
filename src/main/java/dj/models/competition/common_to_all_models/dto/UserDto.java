package dj.models.competition.common_to_all_models.dto;

import dj.models.competition.common_to_all_models.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private User user;

}

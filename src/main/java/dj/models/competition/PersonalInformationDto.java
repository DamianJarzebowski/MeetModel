package dj.models.competition;

import dj.models.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PersonalInformationDto {

    private User user;

}

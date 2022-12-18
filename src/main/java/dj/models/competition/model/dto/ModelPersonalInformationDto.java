package dj.models.competition.model.dto;

import dj.models.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModelPersonalInformationDto {

    private User user;

}

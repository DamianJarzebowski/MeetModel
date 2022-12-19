package dj.models.competition.photographer.dto;

import dj.models.competition.common_to_all_models.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PhotographerPersonalInfoDto {

    private User user;
}

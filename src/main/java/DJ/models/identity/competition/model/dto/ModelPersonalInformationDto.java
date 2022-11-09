package DJ.models.identity.competition.model.dto;

import DJ.models.identity.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModelPersonalInformationDto {

    private User user;

    private int age;
}

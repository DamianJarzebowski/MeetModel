package DJ.models.identity.competition.model.mappers;

import DJ.models.identity.competition.model.Model;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import org.springframework.stereotype.Service;

@Service
public class ModelWriteMapper {

    public Model toEntity(ModelWriteDto dto) {
        return new Model()
                .setAge(dto.getAge())
                .setUser(dto.getUser());

    }
}
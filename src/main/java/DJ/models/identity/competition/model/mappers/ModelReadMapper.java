package DJ.models.identity.competition.model.mappers;

import DJ.models.identity.competition.model.Model;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import org.springframework.stereotype.Service;

@Service
public class ModelReadMapper {

    public ModelReadDto toDto(Model model) {
        return new ModelReadDto()
                .setId(model.getId())
                .setAge(model.getAge())
                .setUser(model.getUser());
    }

    public Model toEntity(ModelReadDto dto) {
        return new Model()
                .setId(dto.getId())
                .setAge(dto.getAge())
                .setUser(dto.getUser());
    }

}

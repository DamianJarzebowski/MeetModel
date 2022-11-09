package DJ.models.identity.competition.model.mappers;

import DJ.models.identity.competition.model.Model;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelReadMapper {

    public ModelReadDto toDto(Model model) {
        return new ModelReadDto()
                .setId(model.getId())
                .setAge(model.getAge())
                .setUser(model.getUser())
                .setSkills(model.getSkills())
                .setAchievements(model.getAchievements())
                .setCharacteristics(model.getCharacteristics());
    }

    public Model toEntity(ModelReadDto dto) {
        return new Model()
                .setId(dto.getId())
                .setAge(dto.getAge())
                .setUser(dto.getUser())
                .setSkills(dto.getSkills())
                .setAchievements(dto.getAchievements())
                .setCharacteristics(dto.getCharacteristics());
    }

    public List<ModelReadDto> toDto(List<Model> models) {
        return models
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}

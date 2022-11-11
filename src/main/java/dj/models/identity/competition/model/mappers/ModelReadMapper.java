package dj.models.identity.competition.model.mappers;

import dj.models.identity.competition.model.Model;
import dj.models.identity.competition.model.dto.ModelReadDto;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ModelReadDto> toDto(List<Model> models) {
        return models
                .stream()
                .map(this::toDto)
                .toList();
    }

}

package dj.models.competition.model.mappers;

import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelReadDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelReadMapper {

    public ModelReadDto toDto(Model model) {
        return new ModelReadDto()
                .setId(model.getId())
                .setUser(model.getUser())
                .setScopeOfWork(model.getScopeOfWork())
                .setSizes(model.getSizes())
                .setAchievements(model.getAchievements());
    }

    public List<ModelReadDto> toDto(List<Model> models) {
        return models
                .stream()
                .map(this::toDto)
                .toList();
    }

}

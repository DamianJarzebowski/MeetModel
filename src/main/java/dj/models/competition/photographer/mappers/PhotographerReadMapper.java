package dj.models.competition.photographer.mappers;

import dj.models.competition.photographer.Photographer;
import dj.models.competition.photographer.dto.PhotographerReadDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerReadMapper {

    public PhotographerReadDto toDto(Photographer photographer) {
        return new PhotographerReadDto()
                .setId(photographer.getId())
                .setUser(photographer.getUser())
                .setScopeOfWork(photographer.getScopeOfWork())
                .setAchievements(photographer.getAchievements());
    }

    public List<PhotographerReadDto> toDto(List<Photographer> photographers) {
        return photographers
                .stream()
                .map(this::toDto)
                .toList();
    }
}

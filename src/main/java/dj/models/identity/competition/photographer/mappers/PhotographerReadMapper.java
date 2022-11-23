package dj.models.identity.competition.photographer.mappers;

import dj.models.identity.competition.photographer.Photographer;
import dj.models.identity.competition.photographer.dto.PhotographerReadDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerReadMapper {

    public PhotographerReadDto toDto(Photographer photographer) {
        return new PhotographerReadDto()
                .setId(photographer.getId())
                .setUser(photographer.getUser())
                .setAchievements(photographer.getAchievements());
    }

    public List<PhotographerReadDto> toDto(List<Photographer> photographers) {
        return photographers
                .stream()
                .map(this::toDto)
                .toList();
    }
}

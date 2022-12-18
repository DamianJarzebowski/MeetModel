package dj.models.competition.photographer.mappers;

import dj.models.competition.photographer.Photographer;
import dj.models.competition.photographer.dto.PhotographerWriteDto;
import org.springframework.stereotype.Service;

@Service
public class PhotographerWriteMapper {

    public Photographer toEntity(PhotographerWriteDto dto) {
        return new Photographer()
                .setUser(dto.getUser())
                .setAchievements(dto.getAchievements());
    }
}

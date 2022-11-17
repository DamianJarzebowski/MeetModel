package dj.models.identity.competition.photographer.mappers;

import dj.models.identity.competition.photographer.Photographer;
import dj.models.identity.competition.photographer.dto.PhotographerWriteDto;
import org.springframework.stereotype.Service;

@Service
public class PhotographerWriteMapper {

    public Photographer toEntity(PhotographerWriteDto dto) {
        return new Photographer()
                .setUser(dto.getUser())
                .setSkills(dto.getSkills())
                .setAchievements(dto.getAchievements());
    }
}

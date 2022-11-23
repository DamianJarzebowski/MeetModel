package dj.models.identity.competition.model.mappers;

import dj.models.identity.competition.model.Model;
import dj.models.identity.competition.model.dto.ModelWriteDto;
import org.springframework.stereotype.Service;

@Service
public class ModelWriteMapper {

    public Model toEntity(ModelWriteDto dto) {
        return new Model()
                .setUser(dto.getUser())
                .setAchievements(dto.getAchievements())
                .setCharacteristics(dto.getCharacteristics());
    }
}

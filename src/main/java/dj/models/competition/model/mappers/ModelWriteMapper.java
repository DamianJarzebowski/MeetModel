package dj.models.competition.model.mappers;

import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelWriteDto;
import org.springframework.stereotype.Service;

@Service
public class ModelWriteMapper {

    public Model toEntity(ModelWriteDto dto) {
        return new Model()
                .setUser(dto.getUser())
                .setSizes(dto.getSizes())
                .setAchievements(dto.getAchievements())
                .setScopeOfWork(dto.getScopeOfWork());
    }
}

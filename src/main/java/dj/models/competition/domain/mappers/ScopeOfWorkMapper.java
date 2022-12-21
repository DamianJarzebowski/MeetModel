package dj.models.competition.domain.mappers;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import org.springframework.stereotype.Service;

@Service
public class ScopeOfWorkMapper {

    public ScopeOfWork toEntity(ScopeOfWorkDto dto) {
        return new ScopeOfWork()
                .setAct(dto.getAct())
                .setCoveredNudity(dto.getCoveredNudity())
                .setEditorial(dto.getEditorial())
                .setFashion(dto.getFashion())
                .setGlamour(dto.getGlamour())
                .setMakeUpAndStylization(dto.getMakeUpAndStylization())
                .setPortrait(dto.getPortrait());
    }
}

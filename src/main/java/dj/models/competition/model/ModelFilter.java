package dj.models.competition.model;

import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.dto.ModelReadDto;

import java.util.List;

public interface ModelFilter {

    List<ModelReadDto> findModelsWithScopeOfWork(ScopeOfWorkDto dto);

}



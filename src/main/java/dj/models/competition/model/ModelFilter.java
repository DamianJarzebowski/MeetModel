package dj.models.competition.model;

import dj.models.competition.model.dto.ModelReadDto;

import java.util.List;

public interface ModelFilter {

    List<ModelReadDto> findModelsWithScopeOfWork(Boolean fashion, Boolean portrait, Boolean glamour, Boolean act,
                                                 Boolean editorial, Boolean coveredNudity, Boolean makeUpAndStylization);

    List<ModelReadDto> findModelBetweenAge(int from, int to);

    List<ModelReadDto> findModelsWithExperience(String experience);

    List<ModelReadDto> findModelsWithHairColor(String hairColor);
}



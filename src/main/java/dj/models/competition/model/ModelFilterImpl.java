package dj.models.competition.model;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.domain.mappers.ScopeOfWorkMapper;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.mappers.ModelReadMapper;
import dj.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelFilterImpl implements ModelFilter {

    private final ModelRepository modelRepository;
    private final ModelReadMapper modelReadMapper;
    private final ScopeOfWorkMapper scopeOfWorkMapper;

    @Override
    public List<ModelReadDto> findModelsWithScopeOfWork(ScopeOfWorkDto dto) {
        var listModels = modelRepository.findAll();
        var mappedListModel = modelReadMapper.toDto(listModels);
        return new ArrayList<>(mappedListModel)
                .stream()
                .filter(model -> filterAboutScopeOfWork(model.getScopeOfWork(), dto))
                .toList();
    }

    /**
     * use in filtering, this method help to look objects us interest
     *
     * @return returns true when the object meets the search requirements
     */
    private Boolean filterAboutScopeOfWork(ScopeOfWork scopeOfWork, ScopeOfWorkDto dto) {

        Field[] scopeOfWorkWanted = scopeOfWorkMapper.toEntity(dto).getClass().getDeclaredFields();
        Field[] scopeOfWorkModel = scopeOfWork.getClass().getDeclaredFields();

        for (int i = 0; i < scopeOfWorkWanted.length; i++) {
            Field value1 = scopeOfWorkWanted[i];
            Field value2 = scopeOfWorkModel[i];

            if (value1.equals(value2))
                continue;
            else if (value2 != null)
                return false;
        }
        return true;
    }


}

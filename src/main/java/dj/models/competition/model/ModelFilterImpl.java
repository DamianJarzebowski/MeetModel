package dj.models.competition.model;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.dto.AgeRangeDto;
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
        return new ArrayList<>(findAllModelsAndMapToRead())
                .stream()
                .filter(model -> filterAboutScopeOfWork(model.getScopeOfWork(), dto))
                .toList();
    }

    /**
     * @return returns true when the object matches the submitted scope of work.
     */
    private boolean filterAboutScopeOfWork(ScopeOfWork scopeOfWork, ScopeOfWorkDto dto) {
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

    @Override
    public List<ModelReadDto> findModelBetweenAge(AgeRangeDto range) {
        return new ArrayList<>(findAllModelsAndMapToRead())
                .stream()
                .filter(model -> filterAboutAge(range, model))
                .toList();
    }

    /**
     * @return returns true when the age of the model is between the given range.
     */
    private boolean filterAboutAge(AgeRangeDto range, ModelReadDto model) {
        int modelAge = model.getUser().getAge();
        if (modelAge == range.getFrom() || modelAge == range.getTo())
            return true;
        else return modelAge >= range.getFrom() && modelAge <= range.getTo();
    }

    private List<ModelReadDto> findAllModelsAndMapToRead() {
        var listModels = modelRepository.findAll();
        return modelReadMapper.toDto(listModels);
    }


}

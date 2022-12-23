package dj.models.competition.model;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
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

    @Override
    public List<ModelReadDto> findModelsWithScopeOfWork(ScopeOfWorkDto dto) {
        return new ArrayList<>(modelReadMapper.toDto(modelRepository.findAll()
                .stream()
                .filter(model -> filterAboutScopeOfWork(model.getScopeOfWork(), dto))
                .toList()));
    }

    /**
     * use in filtering, this method help to look objects us interest
     * @return returns true when the object meets the search requirements
     */
    private Boolean filterAboutScopeOfWork(ScopeOfWork scopeOfWork, ScopeOfWorkDto dto) {
        Field[] fieldsDto = dto.getClass().getDeclaredFields();
        Field[] fieldsModel = scopeOfWork.getClass().getDeclaredFields();

        for (int i = 0; i < fieldsDto.length; i++) {
            Field value1 = fieldsDto[i];
            Field value2 = fieldsModel[i];
            if (value1 != value2 || value1 != null)
                return false;
        }
        return true;
    }


}

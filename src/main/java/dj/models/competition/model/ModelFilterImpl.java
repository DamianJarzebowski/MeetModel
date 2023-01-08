package dj.models.competition.model;

import dj.exception.ErrorMessage;
import dj.exception.badRequest.BadRequestException;
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
    public List<ModelReadDto> findModelBetweenAge(int from, int to) {
        var listModels = modelRepository.findByAgeBetween(from, to);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelsWithExperience(String query) {
        var listModels = modelRepository.searchModelsWithLookingExperience(query);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelsWithHairColor(String hairColor) {

        var enumName = normalizeNameHairColor(hairColor);

        var listModels = modelRepository.searchModelsWithLookingHairColor(enumName);
        return modelReadMapper.toDto(listModels);
    }

    private List<ModelReadDto> findAllModelsAndMapToRead() {
        var listModels = modelRepository.findAll();
        return modelReadMapper.toDto(listModels);
    }

    /**
     * @return the safe enum name
     * @throws BadRequestException if the searched enum does not exist
     */
    public String normalizeNameHairColor(String name) {
        for (Model.HairColor hairColor : Model.HairColor.values()) {
            if (hairColor.name().equalsIgnoreCase(name)) {
                return hairColor.name();
            }
        }
        log.error("Enum hair color: {} does not exists", name);
        throw new BadRequestException(ErrorMessage.BAD_REQUEST);

    }


}
package dj.models.competition.model;

import dj.exception.ErrorMessage;
import dj.exception.badRequest.BadRequestException;
import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
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
    public List<ModelReadDto> findModelsWithScopeOfWork(Boolean fashion, Boolean portrait, Boolean glamour, Boolean act,
                                                        Boolean editorial, Boolean coveredNudity, Boolean makeUpAndStylization) {
        var listModels = modelRepository.findByScopeOfWork(fashion, portrait, glamour, act, editorial, coveredNudity, makeUpAndStylization);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelBetweenAge(int from, int to) {
        var listModels = modelRepository.findByAgeBetween(from, to);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelsWithExperience(String experience) {
        var enumName = normalizeNameExperience(experience);
        var listModels = modelRepository.searchModelsWithLookingExperience(enumName);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelsWithHairColor(String hairColor) {
        var enumName = normalizeNameHairColor(hairColor);
        var listModels = modelRepository.searchModelsWithLookingHairColor(enumName);
        return modelReadMapper.toDto(listModels);
    }

    /**
     * @return the safe enum name hair color
     * @throws BadRequestException if the searched enum does not exist
     */
    private String normalizeNameHairColor(String hairColor) {
        for (Model.HairColor hc : Model.HairColor.values()) {
            if (hc.name().equalsIgnoreCase(hairColor)) {
                return hc.name();
            }
        }
        log.error("Enum hair color: {} does not exists", hairColor);
        throw new BadRequestException(ErrorMessage.BAD_REQUEST);
    }

    /**
     * @return the safe enum name experience
     * @throws BadRequestException if the searched enum does not exist
     */
    private String normalizeNameExperience(String experience) {
        for (User.Experience exp : User.Experience.values()) {
            if (exp.name().equalsIgnoreCase(experience)) {
                return exp.name();
            }
        }
        log.error("Enum experience: {} does not exists", experience);
        throw new BadRequestException(ErrorMessage.BAD_REQUEST);
    }


}
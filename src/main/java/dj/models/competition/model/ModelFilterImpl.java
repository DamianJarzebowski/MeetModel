package dj.models.competition.model;

import dj.exception.ErrorMessage;
import dj.exception.badRequest.BadRequestException;
import dj.models.competition.domain.User;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.mappers.ModelReadMapper;
import dj.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelFilterImpl implements ModelFilter {

    private final ModelRepository modelRepository;
    private final ModelReadMapper modelReadMapper;

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

    @Override
    public List<ModelReadDto> findModelsWithNaturalColor(String naturalColor) {
        var enumName = normalizeNameHairColor(naturalColor);
        var listModels = modelRepository.searchModelsWithLookingNaturalColor(enumName);
        return modelReadMapper.toDto(listModels);
    }

    @Override
    public List<ModelReadDto> findModelsWithHairLength(String hairLength) {
        var enumName = normalizeNameHairLength(hairLength);
        var listModels = modelRepository.searchModelsWithLookingHairLength(enumName);
        return modelReadMapper.toDto(listModels);
    }


    /**
     * If parameters is null normalize his min/max
     */
    @Override
    public List<ModelReadDto> findModelsWithLookingSizes(Integer growthFrom, Integer growthTo,
                                                         Integer weightFrom, Integer weightTo,
                                                         Integer bustFrom, Integer bustTo,
                                                         Integer waistFrom, Integer waistTo,
                                                         Integer hipsFrom, Integer hipsTo) {
        if (growthFrom == null)
            growthFrom = 140;
        if (growthTo == null)
            growthTo = 200;
        if (weightFrom == null)
            weightFrom = 35;
        if (weightTo == null)
            weightTo = 110;
        if (bustFrom == null)
            bustFrom = 75;
        if (bustTo == null)
            bustTo = 120;
        if (waistFrom == null)
            waistFrom = 55;
        if (waistTo == null)
            waistTo = 95;
        if (hipsFrom == null)
            hipsFrom = 80;
        if (hipsTo == null)
            hipsTo = 120;

        var listModels = modelRepository.searchModelsWithLookingSize(growthFrom, growthTo,
                                                                                weightFrom, weightTo,
                                                                                bustFrom, bustTo,
                                                                                waistFrom, waistTo,
                                                                                hipsFrom, hipsTo);
        return modelReadMapper.toDto(listModels);
    }

    /**
     * @return the safe enum name hair length
     * @throws BadRequestException if the searched enum does not exist
     */
    private String normalizeNameHairLength(String hairLength) {
        for (Model.Hair h : Model.Hair.values()) {
            if (h.name().equalsIgnoreCase(hairLength)) {
                return h.name();
            }
        }
        log.error("Enum hair: {} does not exists", hairLength);
        throw new BadRequestException(ErrorMessage.BAD_REQUEST);
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
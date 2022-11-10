package dj.models.identity.competition.model;

import dj.exception.ErrorMessage;
import dj.exception.notFound.NotFoundException;
import dj.models.identity.competition.model.dto.ModelPersonalInformationDto;
import dj.models.identity.competition.model.dto.ModelReadDto;
import dj.models.identity.competition.model.dto.ModelWriteDto;
import dj.models.identity.competition.model.mappers.ModelReadMapper;
import dj.models.identity.competition.model.mappers.ModelWriteMapper;
import dj.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelWriteMapper modelWriteMapper;
    private final ModelReadMapper modelReadMapper;

    @Override
    public ModelReadDto create(ModelWriteDto dto) {
        var model = modelRepository.save(modelWriteMapper.toEntity(dto));
        log.info("Created model {}", model);
        return modelReadMapper.toDto(model);
    }

    @Override
    public ModelReadDto findById(long id) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        log.info("Found model about id: {} ", id);
        return modelReadMapper.toDto(actual);
    }

    @Override
    public List<ModelReadDto> findAll() {
        var listModels = modelRepository.findAll();
        log.info("Found models");
        return modelReadMapper.toDto(listModels);
    }

    @Transactional
    @Override
    public ModelReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        actual
                .setAge(dto.getAge())
                .setUser(dto.getUser());
        log.info("Model about id {} updated", id);
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateSkills(long id, Set<String> skills) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        actual.setSkills(skills);
        log.info("Model about id {} updated", id);
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateAchievements(long id, Set<String> achievements) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        actual.setAchievements(achievements);
        log.info("Model about id {} updated", id);
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateCharacteristic(long id, Set<String> characteristics) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        actual.setCharacteristics(characteristics);
        return modelReadMapper.toDto(actual);
    }

    @Override
    public void delete(long id) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        modelRepository.delete(actual);
        log.info("Model id: {} deleted", id);
    }

    private Model findModelInDataBaseOrThrowNotFoundException(long id) {
        return modelRepository.findById(id).orElseThrow(() -> {
            log.error("Model id: {} does not exists", id);
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
    }

}

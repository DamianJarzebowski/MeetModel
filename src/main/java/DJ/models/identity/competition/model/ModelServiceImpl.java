package DJ.models.identity.competition.model;

import DJ.exception.ErrorMessage;
import DJ.exception.notFound.NotFoundException;
import DJ.models.identity.competition.User;
import DJ.models.identity.competition.model.dto.ModelPersonalInformationDto;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import DJ.models.identity.competition.model.mappers.ModelReadMapper;
import DJ.models.identity.competition.model.mappers.ModelWriteMapper;
import DJ.models.simple.achievement.Achievement;
import DJ.models.simple.characteristic.Characteristic;
import DJ.models.simple.skill.Skill;
import DJ.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        log.info(String.format("Creating model %s", dto.toString()));
        return modelReadMapper.toDto(modelRepository.save(modelWriteMapper.toEntity(dto)));
    }

    @Override
    public ModelReadDto findById(long id) {
        log.info(String.format("Looking model id: %d", id));
        return modelRepository.findById(id).map(modelReadMapper::toDto).orElseThrow(() -> {
            log.error(String.format("Model id: %d does not exists", id));
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
    }

    @Override
    public List<ModelReadDto> findAll() {
        log.info("Downloading all models");
        return modelReadMapper.toDto(modelRepository.findAll());
    }

    @Transactional
    @Override
    public ModelReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        log.info(String.format("Updating model by id: %d", id));
        actual
                .setAge(dto.getAge())
                .setUser(dto.getUser());
        log.info("Model updated");
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateSkills(long id, Set<String> skills) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        log.info(String.format("Updating model by id: %d", id));
        actual.setSkills(skills);
        log.info("Model updated");
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateAchievements(long id, Set<String> achievements) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        log.info(String.format("Updating model by id: %d", id));
        actual.setAchievements(achievements);
        log.info("Model updated");
        return modelReadMapper.toDto(actual);
    }

    @Transactional
    @Override
    public ModelReadDto updateCharacteristic(long id, Set<String> characteristics) {
        var actual = findModelInDataBaseOrThrowNotFoundException(id);
        log.info(String.format("Updating model by id: %d", id));
        actual.setCharacteristics(characteristics);
        log.info("Model updated");
        return modelReadMapper.toDto(actual);
    }

    @Override
    public void delete(long id) {
        modelRepository.deleteById(id);
        log.info(String.format("Model id: %d deleted", id));
    }

    private Model findModelInDataBaseOrThrowNotFoundException(long id) {
        return modelRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Model id: %d does not exists", id));
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
    }

}

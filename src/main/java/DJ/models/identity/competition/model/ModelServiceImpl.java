package DJ.models.identity.competition.model;

import DJ.exception.ErrorMessage;
import DJ.exception.notFound.NotFoundException;
import DJ.models.identity.competition.User;
import DJ.models.identity.competition.model.dto.ModelPersonalInformationDto;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import DJ.models.identity.competition.model.mappers.ModelReadMapper;
import DJ.models.identity.competition.model.mappers.ModelWriteMapper;
import DJ.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public ModelReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto) {
        var actual = modelRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Model id: %d does not exists", id));
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
        log.info(String.format("Updating model by id: %d", id));
        actual
                .setAge(dto.getAge())
                .setUser(dto.getUser());
        log.info("Model updated");
        return modelReadMapper.toDto(actual);
    }

    @Override
    public void delete(long id) {
        modelRepository.deleteById(id);
        log.info(String.format("Model id: %d deleted", id));
    }

}

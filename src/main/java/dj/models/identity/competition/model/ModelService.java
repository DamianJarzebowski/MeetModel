package dj.models.identity.competition.model;

import dj.models.identity.competition.model.dto.ModelPersonalInformationDto;
import dj.models.identity.competition.model.dto.ModelReadDto;
import dj.models.identity.competition.model.dto.ModelWriteDto;

import java.util.List;
import java.util.Set;

public interface ModelService {

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    ModelReadDto findById(long id);

    /**
     * @return list ModelReadDto or empty collection
     */
    List<ModelReadDto> findAll();

    ModelReadDto create(ModelWriteDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    ModelReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    ModelReadDto updateAchievements(long id, Set<String> achievements);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    ModelReadDto updateCharacteristic(long id, Set<String> characteristics);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    void delete(long id);

}

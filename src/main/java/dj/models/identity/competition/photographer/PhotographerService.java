package dj.models.identity.competition.photographer;

import dj.models.identity.competition.model.dto.ModelPersonalInformationDto;
import dj.models.identity.competition.model.dto.ModelWriteDto;
import dj.models.identity.competition.photographer.dto.PhotographerReadDto;

import java.util.List;
import java.util.Set;

public interface PhotographerService {

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto findById(long id);

    /**
     * @return list PhotographerReadDto or empty collection
     */
    List<PhotographerReadDto> findAll();

    PhotographerReadDto create(ModelWriteDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updateSkills(long id, Set<String> skills);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updateAchievements(long id, Set<String> achievements);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    void delete(long id);
}

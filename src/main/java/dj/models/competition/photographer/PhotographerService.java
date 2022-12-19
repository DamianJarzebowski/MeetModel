package dj.models.competition.photographer;

import dj.models.competition.common_to_all_models.dto.ScopeOfWorkDto;
import dj.models.competition.common_to_all_models.dto.UserDto;
import dj.models.competition.photographer.dto.PhotographerReadDto;
import dj.models.competition.photographer.dto.PhotographerWriteDto;

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

    PhotographerReadDto create(PhotographerWriteDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updatePersonalInformation(long id, UserDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updateScopeOfWork(long id, ScopeOfWorkDto dto);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    PhotographerReadDto updateAchievements(long id, Set<String> achievements);

    /**
     * @throws dj.exception.notFound.NotFoundException
     */
    void delete(long id);
}

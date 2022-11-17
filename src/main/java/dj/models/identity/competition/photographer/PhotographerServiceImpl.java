package dj.models.identity.competition.photographer;

import dj.models.identity.competition.photographer.dto.PhotographerPersonalInfoDto;
import dj.models.identity.competition.photographer.dto.PhotographerReadDto;
import dj.models.identity.competition.photographer.dto.PhotographerWriteDto;
import dj.models.identity.competition.photographer.mappers.PhotographerReadMapper;
import dj.models.identity.competition.photographer.mappers.PhotographerWriteMapper;
import dj.exception.ErrorMessage;
import dj.exception.notFound.NotFoundException;
import dj.repository.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final PhotographerWriteMapper photographerWriteMapper;
    private final PhotographerReadMapper photographerReadMapper;

    @Override
    public PhotographerReadDto findById(long id) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        log.info("Found model about id: {} ", id);
        return photographerReadMapper.toDto(actual);
    }

    @Override
    public List<PhotographerReadDto> findAll() {
        var photographerList = photographerRepository.findAll();
        log.info("Found photographer");
        return photographerReadMapper.toDto(photographerList);
    }

    @Override
    public PhotographerReadDto create(PhotographerWriteDto dto) {
        var photographer = photographerRepository.save(photographerWriteMapper.toEntity(dto));
        log.info("Created photographer {}", photographer);
        return photographerReadMapper.toDto(photographer);    }

    @Override
    public PhotographerReadDto updatePersonalInformation(long id, PhotographerPersonalInfoDto dto) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual
                .setUser(dto.getUser());
        log.info("Photographer about id {} updated", id);
        return photographerReadMapper.toDto(actual);    }

    @Override
    public PhotographerReadDto updateSkills(long id, Set<String> skills) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual.setSkills(skills);
        log.info("Photographer about id {} updated skills", id);
        return photographerReadMapper.toDto(actual);    }

    @Override
    public PhotographerReadDto updateAchievements(long id, Set<String> achievements) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual.setAchievements(achievements);
        log.info("Photographer about id {} updated achievements", id);
        return photographerReadMapper.toDto(actual);    }

    @Override
    public void delete(long id) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        photographerRepository.delete(actual);
        log.info("Photographer id: {} deleted", id);
    }

    private Photographer findPhotographerInDataBaseOrThrowNotFoundException(long id) {
        return photographerRepository.findById(id).orElseThrow(() -> {
            log.error("Photographer id: {} does not exists", id);
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
    }
}

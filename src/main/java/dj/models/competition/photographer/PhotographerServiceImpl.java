package dj.models.competition.photographer;

import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.domain.dto.UserDto;
import dj.models.competition.domain.mappers.ScopeOfWorkMapper;
import dj.models.competition.domain.mappers.UserMapper;
import dj.models.competition.photographer.dto.PhotographerReadDto;
import dj.models.competition.photographer.dto.PhotographerWriteDto;
import dj.models.competition.photographer.mappers.PhotographerReadMapper;
import dj.models.competition.photographer.mappers.PhotographerWriteMapper;
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
    private final UserMapper userMapper;
    private final ScopeOfWorkMapper scopeOfWorkMapper;

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
    public PhotographerReadDto updatePersonalInformation(long id, UserDto dto) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual
                .setUser(userMapper.toEntity(dto));
        log.info("Photographer about id {} updated personal information", id);
        return photographerReadMapper.toDto(actual);
    }

    @Override
    public PhotographerReadDto updateScopeOfWork(long id, ScopeOfWorkDto dto) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual
                .setScopeOfWork(scopeOfWorkMapper.toEntity(dto));
        log.info("Photographer about id {} updated scope of work", id);
        return photographerReadMapper.toDto(actual);
    }

    @Override
    public PhotographerReadDto updateAchievements(long id, Set<String> achievements) {
        var actual = findPhotographerInDataBaseOrThrowNotFoundException(id);
        actual.setAchievements(achievements);
        log.info("Photographer about id {} updated achievements", id);
        return photographerReadMapper.toDto(actual);
    }

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

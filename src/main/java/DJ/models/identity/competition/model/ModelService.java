package DJ.models.identity.competition.model;

import DJ.models.identity.competition.model.dto.ModelPersonalInformationDto;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import DJ.models.simple.achievement.Achievement;
import DJ.models.simple.characteristic.Characteristic;
import DJ.models.simple.skill.Skill;

import java.util.List;
import java.util.Set;

public interface ModelService {

    ModelReadDto findById(long id);

    List<ModelReadDto> findAll();

    ModelReadDto create(ModelWriteDto dto);

    ModelReadDto updatePersonalInformation(long id, ModelPersonalInformationDto dto);

    ModelReadDto updateSkills(long id, Set<String> skills);

    ModelReadDto updateAchievements(long id, Set<String> achievements);

    ModelReadDto updateCharacteristic(long id, Set<String> characteristics);

    void delete(long id);

}

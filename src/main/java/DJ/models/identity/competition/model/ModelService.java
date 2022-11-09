package DJ.models.identity.competition.model;

import DJ.models.identity.competition.User;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;

public interface ModelService {

    ModelReadDto create(ModelWriteDto dto);

    ModelReadDto findById(long id);

    ModelReadDto updatePersonalInformation(long id, ModelWriteDto dto);

    void delete(long id);

}

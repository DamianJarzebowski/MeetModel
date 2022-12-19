package dj.models.competition.model.dto;

import dj.models.competition.common_to_all_models.ScopeOfWork;
import dj.models.competition.common_to_all_models.User;
import dj.models.competition.model.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ModelWriteDto {

    private User user;

    private Model.Sizes sizes;

    private ScopeOfWork scopeOfWork;

    private Set<String> achievements = new HashSet<>();

}

package dj.models.competition.model.dto;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.model.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ModelReadDto {

    private Long id;

    private User user;

    private ScopeOfWork scopeOfWork;

    private Model.Sizes sizes;

    private Set<String> achievements = new HashSet<>();

}

package dj.models.competition.photographer.dto;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class PhotographerWriteDto {

    private User user;

    private ScopeOfWork scopeOfWork;

    private Set<String> achievements = new HashSet<>();
}

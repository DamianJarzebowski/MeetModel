package dj.models.competition.domain.dto;

import dj.models.competition.domain.ScopeOfWork;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScopeOfWorkDto {

    private ScopeOfWork scopeOfWork;
}

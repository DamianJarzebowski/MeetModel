package dj.models.competition.common_to_all_models.dto;

import dj.models.competition.common_to_all_models.ScopeOfWork;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScopeOfWorkDto {

    private ScopeOfWork scopeOfWork;
}

package dj.models.competition.common_to_all_models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScopeOfWorkDto {

    private ScopeOfWork scopeOfWork;
}

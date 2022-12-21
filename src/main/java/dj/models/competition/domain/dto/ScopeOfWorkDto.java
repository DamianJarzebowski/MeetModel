package dj.models.competition.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScopeOfWorkDto {

    private Boolean fashion;

    private Boolean portrait;

    private Boolean glamour;

    private Boolean act;

    private Boolean editorial;

    private Boolean coveredNudity;

    private Boolean makeUpAndStylization;

}

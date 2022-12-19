package dj.models.competition;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Accessors(chain = true)
public class ScopeOfWork {

    private Boolean fashion;

    private Boolean portrait;

    private Boolean glamour;

    private Boolean act;

    private Boolean editorial;

    private Boolean coveredNudity;

    private Boolean makeUpAndStylization;

}

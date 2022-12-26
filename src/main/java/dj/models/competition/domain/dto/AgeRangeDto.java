package dj.models.competition.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AgeRangeDto {

    private int from;
    private int to;
}

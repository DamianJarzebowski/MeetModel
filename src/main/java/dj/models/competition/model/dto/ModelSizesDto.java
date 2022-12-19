package dj.models.competition.model.dto;

import dj.models.competition.model.Model;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModelSizesDto {

    private Model.Sizes sizes;
}

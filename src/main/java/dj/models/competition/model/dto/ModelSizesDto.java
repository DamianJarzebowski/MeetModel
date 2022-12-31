package dj.models.competition.model.dto;

import dj.models.competition.model.Model;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModelSizesDto {

    private int growth;

    private int weight;

    private int bust;

    private int waist;

    private int hips;

    private Model.Hair hair;

    private Model.HairColor hairColor;

    private Model.HairColor naturalColor;

    private Model.ClothesSize clothesSize;

    private int footwear;
}

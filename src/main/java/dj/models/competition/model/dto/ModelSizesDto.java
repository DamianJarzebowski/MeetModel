package dj.models.competition.model.dto;

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

    private String hair;

    private String hairColor;

    private String naturalColor;

    private String clothesSize;

    private int footwear;
}

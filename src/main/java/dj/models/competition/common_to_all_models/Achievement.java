package dj.models.competition.common_to_all_models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Accessors(chain = true)
@Embeddable
public class Achievement {

    private String name;
}

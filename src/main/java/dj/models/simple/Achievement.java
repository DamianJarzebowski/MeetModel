package dj.models.simple;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Accessors(chain = true)
@Embeddable
public class Achievement {

    private String name;
}
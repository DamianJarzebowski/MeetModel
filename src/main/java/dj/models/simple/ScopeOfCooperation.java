package dj.models.simple;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Accessors(chain = true)
public class ScopeOfCooperation {

    private String coopType;
    private Boolean declaration;


}
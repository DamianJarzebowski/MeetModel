package DJ.models;

import DJ.models.simple.Benefit;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class Project {

    private Long name;

    private String description;

    private List<Benefit> benefits;

    private int remuneration;

}

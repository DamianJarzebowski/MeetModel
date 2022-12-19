package dj.models.competition;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Embeddable
public class User {

    private String name;

    private String lastName;

    private String description;

    private String experience;

    private String profession;

    private int age;

    private String email;

    @ElementCollection
    @CollectionTable(name = "MODEL_SCOPE_OF_COOPERATION", joinColumns = @JoinColumn(name = "model_id"))
    private HashMap<String, Boolean> scopeOfCooperation = new HashMap<>();

}

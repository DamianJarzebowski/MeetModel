package DJ.models.identity.units.model;

import DJ.models.identity.units.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Model extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private int age;

    @ElementCollection
    @CollectionTable(name = "characteristics", joinColumns = @JoinColumn(name = "model_id"))
    private Set<String> characteristics = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "achievements", joinColumns = @JoinColumn(name = "model_id"))
    private Set<String> achievements = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "model_id"))
    private Set<String> skills = new HashSet<>();
}

package dj.models.competition.model;

import dj.models.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private User user;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "MODEL_ACHIEVEMENTS", joinColumns = @JoinColumn(name = "model_id"))
    private Set<String> achievements = new HashSet<>();

}

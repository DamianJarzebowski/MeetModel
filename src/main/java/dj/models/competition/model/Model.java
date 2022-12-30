package dj.models.competition.model;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "MODELS")
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private User user;

    @Embedded
    private Sizes sizes;

    @Embedded
    private ScopeOfWork scopeOfWork;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "MODEL_ACHIEVEMENTS", joinColumns = @JoinColumn(name = "model_id"))
    private Set<String> achievements = new HashSet<>();

    @Data
    @Embeddable
    @Accessors(chain = true)
    public static class Sizes {

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
}

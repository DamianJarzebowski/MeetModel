package dj.models.competition.model;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
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

    @Valid
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

        @Range(min = 140, max = 200)
        private int growth;

        @Range(min = 35, max = 110)
        private int weight;

        @Range(min = 75, max = 120)
        private int bust;

        @Range(min = 55, max = 95)
        private int waist;

        @Range(min = 80, max = 120)
        private int hips;

        @Enumerated(EnumType.STRING)
        private Hair hair;

        @Enumerated(EnumType.STRING)
        private HairColor hairColor;

        @Enumerated(EnumType.STRING)
        private HairColor naturalColor;

        @Enumerated(EnumType.STRING)
        private ClothesSize clothesSize;

        private int footwear;

    }

    public enum Hair {
        SHORT,
        LONG,
        VERY_LONG;
    }
    public enum HairColor {
        BROWN,
        LIGHT_BLOND,
        BLACK,
        WHITE,
        DARK_BLONDE,
        RED,
        DIFFERENT;
    }
    public enum ClothesSize {
        XS,
        S,
        M,
        L,
        XL,
        XXL;
    }

}

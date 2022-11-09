package DJ.models.identity.competition.photographer;

import DJ.models.identity.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private User user;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "PHOTOGRAPHER_ACHIEVEMENTS", joinColumns = @JoinColumn(name = "photographer_id"))
    private Set<String> achievements = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "PHOTOGRAPHER_SKILLS", joinColumns = @JoinColumn(name = "photographer_id"))
    private Set<String> skills = new HashSet<>();
}
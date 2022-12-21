package dj.models.competition.photographer;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
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

    @Embedded
    private ScopeOfWork scopeOfWork;

    @Version
    private Long version;

    @ElementCollection
    @CollectionTable(name = "PHOTOGRAPHER_ACHIEVEMENTS", joinColumns = @JoinColumn(name = "photographer_id"))
    private Set<String> achievements = new HashSet<>();

}

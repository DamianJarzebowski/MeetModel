package DJ.models.identity.group.companies;

import DJ.models.identity.group.project.Project;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;

    private String description;

    @OneToMany(mappedBy = "companies")
    private Set<Project> projects = new HashSet<>();
}

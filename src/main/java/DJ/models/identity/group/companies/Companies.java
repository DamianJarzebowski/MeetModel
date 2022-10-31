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
    private Long id;

    private String name;

    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "companies_projects", joinColumns = @JoinColumn(name = "companies_id"))
    @Column(name = "projects")
    private Set<Project> projects = new HashSet<>();
}

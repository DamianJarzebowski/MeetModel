package DJ.models.identity.group.companies;

import DJ.models.identity.group.project.Project;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

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
    private List<Project> project;
}

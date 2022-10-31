package DJ.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

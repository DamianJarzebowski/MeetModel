package dj.models.identity.other;

import dj.models.identity.other.Companies;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private Long name;

    private String description;

    private int remuneration;

    @ElementCollection
    @CollectionTable(name = "PROJECT_BENEFITS", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> benefits = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "PROJECT_SKILLS", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> skills = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "PROJECT_CHARACTERISTICS", joinColumns = @JoinColumn(name = "project_id"))
    private Set<String> characteristics = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "companies_id")
    private Companies companies;
}

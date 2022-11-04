package DJ.dao;

import DJ.models.identity.group.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDAO extends JpaRepository<Project, Long> {
}

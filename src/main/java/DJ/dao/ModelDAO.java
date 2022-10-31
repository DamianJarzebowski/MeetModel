package DJ.dao;

import DJ.models.identity.units.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelDAO extends JpaRepository<Model, Long> {
}

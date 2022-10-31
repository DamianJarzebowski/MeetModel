package DJ.dao;

import DJ.models.identity.units.photographer.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerDAO extends JpaRepository<Photographer, Long> {
}

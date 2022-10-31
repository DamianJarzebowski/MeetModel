package DJ.dao;

import DJ.models.identity.group.companies.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesDAO extends JpaRepository<Companies, Long> {
}

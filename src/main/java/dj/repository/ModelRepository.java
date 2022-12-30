package dj.repository;

import dj.models.competition.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(value = "SELECT * FROM MODELS m WHERE " +
    "m.experience = :query", nativeQuery = true)
    List<Model> searchModelsWithLookingExperience(String query);


}

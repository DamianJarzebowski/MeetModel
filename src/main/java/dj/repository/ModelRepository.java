package dj.repository;

import dj.models.competition.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(value = "SELECT * FROM MODELS m WHERE " +
    "m.experience = :experience", nativeQuery = true)
    List<Model> searchModelsWithLookingExperience(String experience);

    @Query(value = "SELECT * FROM MODELS m WHERE " +
            "m.hair_color = :query", nativeQuery = true)
    List<Model> searchModelsWithLookingHairColor(String query);

    @Query(value = "SELECT * FROM MODELS m WHERE " +
            "m.natural_color = :query", nativeQuery = true)
    List<Model> searchModelsWithLookingNaturalColor(String query);

    @Query(value = "SELECT * FROM MODELS m WHERE " +
            "m.age BETWEEN :from AND :to", nativeQuery = true)
    List<Model> findByAgeBetween(int from, int to);
}

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
            "m.hair_color = :hairColor", nativeQuery = true)
    List<Model> searchModelsWithLookingHairColor(String hairColor);

    @Query(value = "SELECT * FROM MODELS m WHERE " +
            "m.natural_color = :naturalColor", nativeQuery = true)
    List<Model> searchModelsWithLookingNaturalColor(String naturalColor);

    @Query(value = "SELECT * FROM MODELS m WHERE " +
            "m.age BETWEEN :from AND :to", nativeQuery = true)
    List<Model> findByAgeBetween(int from, int to);

    @Query(value =
            "SELECT * FROM MODELS m " +
            "WHERE (:fashion IS NULL OR m.fashion = :fashion) " +
            "AND (:portrait IS NULL OR m.portrait = :portrait) " +
            "AND (:glamour IS NULL OR m.glamour = :glamour) " +
            "AND (:act IS NULL OR m.act = :act) " +
            "AND (:editorial IS NULL OR m.editorial = :editorial) " +
            "AND (:coveredNudity IS NULL OR m.covered_nudity = :coveredNudity) " +
            "AND (:makeUpAndStylization IS NULL OR m.make_up_and_stylization = :makeUpAndStylization)", nativeQuery = true)
    List<Model> findByScopeOfWork(Boolean fashion, Boolean portrait, Boolean glamour, Boolean act,
                                  Boolean editorial, Boolean coveredNudity, Boolean makeUpAndStylization);

    @Query(value =
    "SELECT * FROM MODELS m " +
    "WHERE m.hair = :hairLength", nativeQuery = true)
    List<Model> searchModelsWithLookingHairLength(String hairLength);

    @Query(value =
            "SELECT * FROM MODELS m " +
            "WHERE (m.growth BETWEEN :growthFrom AND :growthTo) " +
            "AND (m.weight BETWEEN :weightFrom AND :weightTo) " +
            "AND (m.bust BETWEEN :bustFrom AND :bustTo) " +
            "AND (m.waist BETWEEN :waistFrom AND :waistTo) " +
            "AND (m.hips BETWEEN :hipsFrom AND :hipsTo)" , nativeQuery = true)
    List<Model> searchModelsWithLookingSize(int growthFrom, int growthTo,
                                            int weightFrom, int weightTo,
                                            int bustFrom, int bustTo,
                                            int waistFrom, int waistTo,
                                            int hipsFrom, int hipsTo);
}

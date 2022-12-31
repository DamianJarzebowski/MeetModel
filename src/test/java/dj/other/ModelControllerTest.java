package dj.other;

import dj.exception.notFound.NotFoundException;
import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.domain.mappers.ScopeOfWorkMapper;
import dj.models.competition.domain.mappers.UserMapper;
import dj.models.competition.model.Model;
import dj.models.competition.model.ModelService;
import dj.models.competition.domain.dto.UserDto;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.dto.ModelSizesDto;
import dj.models.competition.model.dto.ModelWriteDto;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static dj.other.CRUD_Test.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ModelControllerTest {

    public static final String BASE_URL = "/api/model";
    private String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ModelService modelService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ScopeOfWorkMapper scopeOfWorkMapper;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    private ModelWriteDto dateToCreateModel() {
        return new ModelWriteDto()
                .setUser(new User()
                        .setName("Ala")
                        .setLastName("Nowak")
                        .setDescription("abcd")
                        .setExperience(User.Experience.SMALL)
                        .setProfession(User.Profession.MODEL)
                        .setAge(18)
                        .setEmail("email@gmail.com"))
                .setAchievements(new HashSet<>(
                        Set.of("Achievement1", "Achievement2")))
                .setScopeOfWork(new ScopeOfWork()
                        .setAct(true)
                        .setCoveredNudity(true)
                        .setEditorial(true)
                        .setFashion(true)
                        .setGlamour(true)
                        .setMakeUpAndStylization(true)
                        .setPortrait(true))
                .setSizes(new Model.Sizes()
                        .setGrowth(170)
                        .setWeight(60)
                        .setBust(90)
                        .setWaist(70)
                        .setHips(90)
                        .setHair(Model.Hair.SHORT)
                        .setHairColor(Model.HairColor.LIGHT_BLOND)
                        .setNaturalColor(Model.HairColor.LIGHT_BLOND)
                        .setClothesSize(Model.ClothesSize.S)
                        .setFootwear(36));
    }

    // For looking -1L always will be return exception bcs id will be never under 1
    @Test
    void shouldThrowNotFoundException() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            modelService.findById(-1L);
        });
        String expectedMessage = "Resource does not exist";
        String actualMessage = exception.getErrorMessage().getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldCreateAndGetModel() {
        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);
        var actual = read(modelLocation, ModelReadDto.class, HttpStatus.SC_OK);

        var expected = new ModelReadDto()
                .setId(actual.getId())
                .setUser(dateToCreateModel().getUser())
                .setSizes(dateToCreateModel().getSizes())
                .setAchievements(dateToCreateModel().getAchievements())
                .setScopeOfWork(dateToCreateModel().getScopeOfWork());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdatePersonalInformation() {

        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);

        var dateToUpdate = new UserDto()
                        .setName("Paulina")
                        .setLastName("Nowak")
                        .setDescription("qwer")
                        .setProfession(User.Profession.MODEL)
                        .setExperience(User.Experience.MEDIUM)
                        .setAge(18)
                        .setEmail("newEmail@gmail.com");

        var updated = update(modelLocation + "/general", ModelReadDto.class, dateToUpdate, HttpStatus.SC_OK);

        var expected = updated
                .setUser(userMapper.toEntity(dateToUpdate));

        Assertions.assertThat(updated).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdateModelSizes() {

        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);

        var dateToUpdate = new ModelSizesDto()
                        .setGrowth(175)
                        .setWeight(65)
                        .setBust(95)
                        .setWaist(75)
                        .setHips(95)
                        .setHair(Model.Hair.LONG)
                        .setHairColor(Model.HairColor.RED)
                        .setNaturalColor(Model.HairColor.BLACK)
                        .setClothesSize(Model.ClothesSize.M)
                        .setFootwear(36);

        var updated = update(modelLocation + "/sizes", ModelReadDto.class, dateToUpdate, HttpStatus.SC_OK);

        var expected = updated
                .setSizes(new Model.Sizes()
                        .setGrowth(175)
                        .setWeight(65)
                        .setBust(95)
                        .setWaist(75)
                        .setHips(95)
                        .setHair(Model.Hair.LONG)
                        .setHairColor(Model.HairColor.RED)
                        .setNaturalColor(Model.HairColor.BLACK)
                        .setClothesSize(Model.ClothesSize.M)
                        .setFootwear(36));

        Assertions.assertThat(updated).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdateScopeOfWork() {

        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);

        var dateToUpdate = new ScopeOfWorkDto()
                .setAct(false)
                .setCoveredNudity(false)
                .setEditorial(false)
                .setFashion(false)
                .setGlamour(false)
                .setMakeUpAndStylization(false)
                .setPortrait(false);

        var updated = update(modelLocation + "/scopeOfWork", ModelReadDto.class, dateToUpdate, HttpStatus.SC_OK);

        var expected = updated
                .setScopeOfWork(scopeOfWorkMapper.toEntity(dateToUpdate));

        Assertions.assertThat(updated).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdateListAchievements() {

        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);

        var achievementToUpdate = new HashSet<>(Set.of("NewAchievement1", "NewAchievement2"));

        update(modelLocation + "/achievements", ModelReadDto.class, achievementToUpdate, HttpStatus.SC_OK);

        var actual = read(modelLocation, ModelReadDto.class, HttpStatus.SC_OK);

        var expected = actual
                .setAchievements(new HashSet<>(Set.of("NewAchievement1", "NewAchievement2")));

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndDeleteModel() {
        var modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);
        delete(modelLocation, HttpStatus.SC_NO_CONTENT);
    }

}


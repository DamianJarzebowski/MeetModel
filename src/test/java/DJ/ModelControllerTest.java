package DJ;

import DJ.models.identity.competition.User;
import DJ.models.identity.competition.model.dto.ModelPersonalInformationDto;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static DJ.CRUD_Test.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ModelControllerTest {

    public static final String BASE_URL = "/api/model";
    private String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    private ModelWriteDto dateToCreateModel() {

        User user = new User()
                .setName("Ala")
                .setLastName("Nowak")
                .setDescription("abcd")
                .setEmail("email@gmail.com");

        return new ModelWriteDto()
                .setUser(user)
                .setAge(18)
                .setSkills(new HashSet<>(
                        Set.of("Skill1", "Skill2")
                ))
                .setAchievements(new HashSet<>(
                        Set.of("Achievement1", "Achievement2")
                ))
                .setCharacteristics(new HashSet<>(
                        Set.of("Characteristics1", "Characteristics2")
                ));
    }

    @Test
    void shouldCreateAndGetModel() {

        var modelLocation = create(baseUri, dateToCreateModel());

        var actual = read(modelLocation, ModelReadDto.class);

        var expected = new ModelReadDto()
                .setId(actual.getId())
                .setUser(dateToCreateModel().getUser())
                .setAge(dateToCreateModel().getAge())
                .setSkills(dateToCreateModel().getSkills())
                .setAchievements(dateToCreateModel().getAchievements())
                .setCharacteristics(dateToCreateModel().getCharacteristics());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdatePersonalInformation() {

        var modelLocation = create(baseUri, dateToCreateModel());

        var dateToUpdate = new ModelPersonalInformationDto()
                .setAge(20)
                .setUser(new User()
                        .setName("Paulina")
                        .setLastName("Nowak")
                        .setDescription("qwer")
                        .setEmail("newEmail@gmail.com")
                );

        var updated = update(modelLocation + "/general", ModelReadDto.class, dateToUpdate);

        var expected = updated
                .setUser(dateToUpdate.getUser())
                .setAge(dateToUpdate.getAge());

        Assertions.assertThat(updated).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndUpdateListSkillAchievementsCharacteristics() {

        var modelLocation = create(baseUri, dateToCreateModel());

        var skillToUpdate = new HashSet<>(Set.of("NewSkill1", "NewSkill2"));
        var achievementToUpdate = new HashSet<>(Set.of("NewAchievement1", "NewAchievement2"));
        var characteristicsToUpdate = new HashSet<>(Set.of("NewCharacteristics1", "NewCharacteristics2"));

        update(modelLocation + "/skills", ModelReadDto.class, skillToUpdate);
        update(modelLocation + "/achievements", ModelReadDto.class, achievementToUpdate);
        update(modelLocation + "/characteristics", ModelReadDto.class, characteristicsToUpdate);

        var actual = read(modelLocation, ModelReadDto.class);

        var expected = actual
                .setSkills(new HashSet<>(Set.of("NewSkill1", "NewSkill2")))
                .setAchievements(new HashSet<>(Set.of("NewAchievement1", "NewAchievement2")))
                .setCharacteristics(new HashSet<>(Set.of("NewCharacteristics1", "NewCharacteristics2")));

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateAndDeleteModel() {

        var modelLocation = create(baseUri, dateToCreateModel());

        delete(modelLocation);
    }

}


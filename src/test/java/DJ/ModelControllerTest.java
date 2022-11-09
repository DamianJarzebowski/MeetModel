package DJ;

import DJ.models.identity.competition.User;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

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

    @Test
    void shouldCreateAndGetModel() {

        User user = new User()
                .setName("Ala")
                .setLastName("Nowak")
                .setDescription("abcd")
                .setEmail("email@gmail.com");

        var modelWriteDto = new ModelWriteDto()
                .setUser(user)
                .setAge(18);

        var modelLocation = create(baseUri, modelWriteDto);

        var actual = read(modelLocation, ModelReadDto.class);

        var expected = new ModelReadDto()
                .setId(actual.getId())
                .setUser(user)
                .setAge(18);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

}


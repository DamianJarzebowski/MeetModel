package dj.hellocucumber.model_test;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelSizesDto;
import dj.models.competition.model.mappers.ModelSizesMapper;
import dj.other.CRUD_Test;
import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.UserDto;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.dto.ModelWriteDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static dj.other.CRUD_Test.*;
import static dj.other.CRUD_Test.delete;
import static dj.other.CRUD_Test.update;

public class ModelStepDefCRUD {

    private static final String baseUri = "http://localhost:8080/api/model";

    private String modelLocation;
    private ModelReadDto actualReadModel;

    private ModelWriteDto dateToCreateModel() {
        return new ModelWriteDto()
                .setUser(new User()
                        .setName("Ala")
                        .setLastName("Nowak")
                        .setDescription("abcd")
                        .setExperience(User.Experience.SMALL)
                        .setProfession("Model")
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
                        .setHair("long")
                        .setHairColor("Black")
                        .setNaturalColor("Black")
                        .setClothesSize("S")
                        .setFootwear(36));
    }

    @Given("Create a new model.")
    public void create_a_new_model() {
        modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);
    }

    @When("Read created model.")
    public void read_created_model() {
        actualReadModel = read(modelLocation, ModelReadDto.class, HttpStatus.SC_OK);
    }

    @Then("Created model should be as read.")
    public void created_model_should_be_as_read() {
        var expected = new ModelReadDto()
                .setId(actualReadModel.getId())
                .setUser(dateToCreateModel().getUser())
                .setSizes(dateToCreateModel().getSizes())
                .setScopeOfWork(dateToCreateModel().getScopeOfWork())
                .setAchievements(dateToCreateModel().getAchievements());

        Assertions.assertThat(actualReadModel).isEqualTo(expected);
    }

    @When("Update personal information about model.")
    public void update_personal_information_about_model() {
        UserDto generalDateToUpdate = new UserDto()
                            .setName("Ala")
                            .setLastName("Kowalski")
                            .setDescription("qwerty")
                            .setExperience(User.Experience.MEDIUM)
                            .setProfession("Model")
                            .setAge(19)
                            .setEmail("email123@gmail.com");

        update( modelLocation + "/general", ModelReadDto.class, generalDateToUpdate, HttpStatus.SC_OK);
    }

    @Then("Check correct data change general.")
    public void check_correct_data_change_general() {
        var expected = actualReadModel
                .setUser(new User()
                        .setName("Ala")
                        .setLastName("Kowalski")
                        .setDescription("qwerty")
                        .setExperience(User.Experience.MEDIUM)
                        .setProfession("Model")
                        .setAge(19)
                        .setEmail("email123@gmail.com"));

        Assertions.assertThat(actualReadModel).isEqualTo(expected);
    }

    @When("Update sizes model.")
    public void updateSizesModel() {
        ModelSizesDto sizesDateToUpdate = new ModelSizesDto()
                        .setGrowth(175)
                        .setWeight(65)
                        .setBust(95)
                        .setWaist(75)
                        .setHips(95)
                        .setHair("short")
                        .setHairColor("Red")
                        .setNaturalColor("Black")
                        .setClothesSize("M")
                        .setFootwear(36);

        update(modelLocation + "/sizes", ModelReadDto.class, sizesDateToUpdate, HttpStatus.SC_OK);
    }

    @Then("Check correct data change sizes.")
    public void checkCorrectDataChangeSizes() {
        var expected = actualReadModel
                .setSizes(new Model.Sizes()
                        .setGrowth(175)
                        .setWeight(65)
                        .setBust(95)
                        .setWaist(75)
                        .setHips(95)
                        .setHair("short")
                        .setHairColor("Red")
                        .setNaturalColor("Black")
                        .setClothesSize("M")
                        .setFootwear(36));

        Assertions.assertThat(actualReadModel).isEqualTo(expected);
    }

    @When("Update scope of work")
    public void updateScopeOfWork() {
        ScopeOfWorkDto scopeOfWorkToUpdate = new ScopeOfWorkDto()
                        .setAct(false)
                        .setCoveredNudity(false)
                        .setEditorial(false)
                        .setFashion(false)
                        .setGlamour(false)
                        .setMakeUpAndStylization(false)
                        .setPortrait(false);

        update(modelLocation + "/scopeOfWork", ModelReadDto.class, scopeOfWorkToUpdate, HttpStatus.SC_OK);
    }

    @Then("check correct date change scope of work.")
    public void checkCorrectDateChangeScopeOfWork() {
        var expected = actualReadModel
                .setScopeOfWork((new ScopeOfWork()
                        .setAct(false)
                        .setCoveredNudity(false)
                        .setEditorial(false)
                        .setFashion(false)
                        .setGlamour(false)
                        .setMakeUpAndStylization(false)
                        .setPortrait(false)));

        Assertions.assertThat(actualReadModel).isEqualTo(expected);
    }

    @When("Update lists achievements model.")
    public void update_lists_achievements_model() {
        var achievementToUpdate = new HashSet<>(Set.of("NewAchievement1", "NewAchievement2"));
        update(modelLocation + "/achievements", ModelReadDto.class, achievementToUpdate, HttpStatus.SC_OK);
    }

    @Then("Check correct data change lists achievements model.")
    public void check_correct_data_change_lists_achievements_model() {
        var expected = actualReadModel
                .setAchievements(new HashSet<>(Set.of("NewAchievement1", "NewAchievement2")));

        Assertions.assertThat(actualReadModel).isEqualTo(expected);
    }

    @When("Deleting model.")
    public void deleting_model() {
        delete(modelLocation, HttpStatus.SC_NO_CONTENT);
    }

    @Then("Try read id deleted model, if return HTTP response status code NotFound will be ok.")
    public void try_read_id_deleted_model_if_return_http_response_status_code_not_found_will_be_ok() {
        RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .get(modelLocation)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }




}

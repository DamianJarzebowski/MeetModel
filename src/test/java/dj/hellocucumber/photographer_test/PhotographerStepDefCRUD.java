package dj.hellocucumber.photographer_test;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.domain.dto.UserDto;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.photographer.dto.PhotographerReadDto;
import dj.models.competition.photographer.dto.PhotographerWriteDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

import java.util.HashSet;
import java.util.Set;

import static dj.other.CRUD_Test.*;

public class PhotographerStepDefCRUD {

    private static final String baseUri = "http://localhost:8080/api/photographer";

    private String photographerLocation;
    private PhotographerReadDto actualReadPhotographer;

    private PhotographerWriteDto dataToCreatePhotographer() {
        return new PhotographerWriteDto()
                .setUser(new User()
                        .setName("Ala")
                        .setLastName("Nowak")
                        .setDescription("abcd")
                        .setExperience("Medium")
                        .setProfession("Photographer")
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
                        .setPortrait(true));
    }

    @Given("Create a new photographer.")
    public void createANewPhotographer() {
        photographerLocation = create(baseUri, dataToCreatePhotographer(), HttpStatus.SC_CREATED);
    }

    @When("Read created photographer.")
    public void readCreatedPhotographer() {
         actualReadPhotographer = read(photographerLocation, PhotographerReadDto.class, HttpStatus.SC_OK);
    }

    @Then("Created photographer should be as read.")
    public void createdPhotographerShouldBeAsRead() {
        var expected = new PhotographerReadDto()
                .setId(actualReadPhotographer.getId())
                .setUser(dataToCreatePhotographer().getUser())
                .setAchievements(dataToCreatePhotographer().getAchievements())
                .setScopeOfWork(dataToCreatePhotographer().getScopeOfWork());

        Assertions.assertThat(expected).isEqualTo(actualReadPhotographer);
    }

    @When("Update personal information about photographer.")
    public void updatePersonalInformationAboutPhotographer() {
        UserDto generalDateToUpdate = new UserDto()
                .setUser(new User()
                        .setName("Ula")
                        .setLastName("Pawlowsky")
                        .setDescription("qwerty")
                        .setExperience("Master")
                        .setProfession("Photographer")
                        .setAge(19)
                        .setEmail("email123@gmail.com"));

        update(photographerLocation + "/general", PhotographerReadDto.class, generalDateToUpdate, HttpStatus.SC_OK);
    }

    @Then("Check if data photographer, was have correct change general data.")
    public void checkIfDataPhotographerWasHaveCorrectChangeGeneralData() {
        var expected = actualReadPhotographer
                .setUser(new User()
                        .setName("Ula")
                        .setLastName("Pawlowsky")
                        .setDescription("qwerty")
                        .setExperience("Master")
                        .setProfession("Photographer")
                        .setAge(19)
                        .setEmail("email123@gmail.com"));

        Assertions.assertThat(expected).isEqualTo(actualReadPhotographer);
    }

    @When("Update scope of work photographer.")
    public void updateScopeOfWorkPhotographer() {
        ScopeOfWorkDto scopeOfWorkToUpdate = new ScopeOfWorkDto()
                .setScopeOfWork(new ScopeOfWork()
                        .setAct(false)
                        .setCoveredNudity(false)
                        .setEditorial(false)
                        .setFashion(false)
                        .setGlamour(false)
                        .setMakeUpAndStylization(false)
                        .setPortrait(false));

        update(photographerLocation + "/scopeOfWork", PhotographerReadDto.class, scopeOfWorkToUpdate, HttpStatus.SC_OK);
    }

    @Then("Check if data photographer, was have correct change scope of work.")
    public void checkIfDataPhotographerWasHaveCorrectChangeScopeOfWork() {
        var expected = actualReadPhotographer
                .setScopeOfWork((new ScopeOfWork()
                        .setAct(false)
                        .setCoveredNudity(false)
                        .setEditorial(false)
                        .setFashion(false)
                        .setGlamour(false)
                        .setMakeUpAndStylization(false)
                        .setPortrait(false)));

        Assertions.assertThat(expected).isEqualTo(actualReadPhotographer);
    }

    @When("Update lists achievements photographer.")
    public void updateListsAchievementsPhotographer() {
        var achievementToUpdate = new HashSet<>(Set.of("NewAchievement1", "NewAchievement2"));
        update(photographerLocation + "/achievements", PhotographerReadDto.class, achievementToUpdate, HttpStatus.SC_OK);
    }

    @Then("Then Check correct data change lists achievements photographer.")
    public void thenCheckCorrectDataChangeListsAchievementsPhotographer() {
        var expected = actualReadPhotographer
                .setAchievements(new HashSet<>(Set.of("NewAchievement1", "NewAchievement2")));

        Assertions.assertThat(expected).isEqualTo(actualReadPhotographer);
    }

    @When("Deleting photographer.")
    public void deletingPhotographer() {
        delete(photographerLocation, HttpStatus.SC_NO_CONTENT);
    }

    @Then("Try read id deleted photographer, if return HTTP response status code NotFound will be ok.")
    public void tryReadIdDeletedPhotographerIfReturnHTTPResponseStatusCodeNotFoundWillBeOk() {
        RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .get(photographerLocation)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}

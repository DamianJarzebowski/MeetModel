package dj.hellocucumber.filter;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.dto.ModelWriteDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

import java.util.*;

import static dj.other.CRUD_Test.*;

public class FilterStepDef {

    private static final String baseUri = "http://localhost:8080/api/model";
    private static final String filter = "http://localhost:8080/api/filter";


    private String modelLocation;
    private List<ModelReadDto> filteredList = new ArrayList<>();

    private ModelWriteDto dateToCreateModel() {
        return new ModelWriteDto()
                .setUser(new User()
                        .setName("Ala")
                        .setLastName("Nowak")
                        .setDescription("abcd")
                        .setExperience("Medium")
                        .setProfession("Model")
                        .setAge(18)
                        .setEmail("email@gmail.com"))
                .setAchievements(new HashSet<>(
                        Set.of("Achievement1", "Achievement2")))
                .setScopeOfWork(new ScopeOfWork()
                        .setAct(false)
                        .setCoveredNudity(false)
                        .setEditorial(false)
                        .setFashion(false)
                        .setGlamour(true)
                        .setMakeUpAndStylization(false)
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

    @Given("Create a new model with looking scope of work.")
    public void createANewModelWithLookingScopeOfWork() {
        modelLocation = create(baseUri, dateToCreateModel(), HttpStatus.SC_CREATED);
    }

    @When("Try find created model.")
    public void tryFindCreatedModel() {
        ScopeOfWorkDto looking = new ScopeOfWorkDto()
                .setGlamour(true)
                .setPortrait(true);

        filteredList.addAll(
                RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .body(looking)
                .get(filter)
                .as(Collection.class)
        );

    }

    @Then("Check if was found.")
    public void checkIfWasFound() {

        ModelReadDto foundModel = filteredList.get(0);

        ModelReadDto createdModel = read(modelLocation, ModelReadDto.class, HttpStatus.SC_OK);

        Assertions.assertThat(foundModel.getId()).isEqualTo(createdModel.getId());
    }
}

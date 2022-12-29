package dj.hellocucumber.filter;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.AgeRangeDto;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.dto.ModelWriteDto;
import dj.repository.ModelRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static dj.other.CRUD_Test.*;

public class FilterStepDef {

    @Autowired
    private ModelRepository modelRepository;

    private static final String baseUri = "http://localhost:8080/api/model";
    private static final String filter = "http://localhost:8080/api/filter";


    private String modelLocation;
    private final List<ModelReadDto> filteredListScopeOfWork = new ArrayList<>();
    private List<ModelReadDto> filteredListAboutAge = new ArrayList<>();

    private final ModelWriteDto dateToCreateModel = new ModelWriteDto()
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

    @Given("Create a new model with looking scope of work.")
    public void createANewModelWithLookingScopeOfWork() {
        modelLocation = create(baseUri, dateToCreateModel, HttpStatus.SC_CREATED);
    }

    @When("Try find created model.")
    public void tryFindCreatedModel() {
        ScopeOfWorkDto wanted = new ScopeOfWorkDto()
                .setGlamour(true)
                .setPortrait(true);

        var filterItem = RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .body(wanted)
                .get(filter + "/scopeOfWork")
                .as(new TypeRef<List<ModelReadDto>>() {
                });

        filteredListScopeOfWork.addAll(filterItem);
    }

    @Then("Check if was found.")
    public void checkIfWasFound() {

        var foundModel = filteredListScopeOfWork.get(0);

        var createdModel = read(modelLocation, ModelReadDto.class, HttpStatus.SC_OK);

        Assertions.assertThat(foundModel.getId()).isEqualTo(createdModel.getId());
    }

    @Given("Create a new model with an age {int}")
    public void createANewModelWithAge(Integer age) {
        ModelWriteDto modelToCreate = dateToCreateModel
                .setUser(dateToCreateModel.getUser()
                        .setAge(age));
        modelLocation = create(baseUri, modelToCreate, HttpStatus.SC_CREATED);
    }

    @When("Try find models with age from {int} to {int}")
    public void tryFindModelsWithAgeFromTo(Integer from, Integer to) {

        AgeRangeDto range = new AgeRangeDto()
                .setFrom(from)
                .setTo(to);

        var filterItem = RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .body(range)
                .post(filter + "/age")
                .as(new TypeRef<List<ModelReadDto>>() {});

        filteredListAboutAge.clear();
        filteredListAboutAge.addAll(filterItem);
    }

    @Then("Size collected list will be equal {int}")
    public void sizeCollectedListWillBeSize(Integer size) {

        Assertions.assertThat(filteredListAboutAge).hasSize(size);
        modelRepository.deleteAll();
    }
}

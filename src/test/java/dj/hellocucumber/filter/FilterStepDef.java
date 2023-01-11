package dj.hellocucumber.filter;

import dj.models.competition.domain.ScopeOfWork;
import dj.models.competition.domain.User;
import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.model.dto.ModelWriteDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

import java.util.*;

import static dj.other.CRUD_Test.*;

public class FilterStepDef {

    private static final String baseUri = "http://localhost:8080/api/model";
    private static final String filter = "http://localhost:8080/api/filter";

    private String modelLocation;
    private final List<ModelReadDto> listModels = new ArrayList<>();

    // Please don't change this model data because tests operating about data this model
    private final ModelWriteDto dateToCreateModel = new ModelWriteDto()
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
                    .setHair(Model.Hair.SHORT)
                    .setHairColor(Model.HairColor.LIGHT_BLOND)
                    .setNaturalColor(Model.HairColor.LIGHT_BLOND)
                    .setClothesSize(Model.ClothesSize.S)
                    .setFootwear(36));

    @Given("Create a new model from data dateToCreateModel.")
    public void Create_a_new_model_from_data_dateToCreateModel() {
        modelLocation = create(baseUri, dateToCreateModel, HttpStatus.SC_CREATED);
    }

    @When("^Try find created model using data examples from table (.*) (.*) (.*) (.*) (.*) (.*) (.*)$")
    public void try_find_created_model_using_data_examples(
            String act, String coveredNudity, String editorial, String fashion,
            String glamour, String makeUpAndStylization, String portrait) {

        var filterItem = RestAssured
                .given()
                .pathParam("act", act)
                .pathParam("coveredNudity", coveredNudity)
                .pathParam("editorial", editorial)
                .pathParam("fashion", fashion)
                .pathParam("glamour", glamour)
                .pathParam("makeUpAndStylization", makeUpAndStylization)
                .pathParam("portrait", portrait)
                .headers("Content-Type", ContentType.JSON)
                .get(filter + "/scopeOfWork" + "?act={act}&coveredNudity={coveredNudity}&editorial={editorial}&fashion={fashion}&glamour={glamour}&makeUpAndStylization={makeUpAndStylization}&portrait={portrait}")
                .as(new TypeRef<List<ModelReadDto>>() {
                });

        listModels.addAll(filterItem);
    }

    @Then("^Check if was found (.*)$")
    public void check_if_was_found(String found) {
        Assertions.assertThat(expectedResult(found)).isEqualTo(result(listModels));
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

        var filterItem = RestAssured
                .given()
                .pathParam("from", from)
                .pathParam("to", to)
                .headers("Content-Type", ContentType.JSON)
                .get(filter + "/age" + "?from={from}&to={to}")
                .as(new TypeRef<List<ModelReadDto>>() {
                });

        listModels.clear();
        listModels.addAll(filterItem);
    }

    @Then("Size collected list will be equal {int}")
    public void sizeCollectedListWillBeSize(Integer size) {
        Assertions.assertThat(listModels).hasSize(size);
    }


    @When("^Try find created model using data examples experience: (.*)$")
    public void tryFindCreatedModelUsingDataExamplesExperienceExperience(String experience) {

        var filterItem = RestAssured
                .given()
                .pathParam("experience", experience)
                .headers("Content-Type", ContentType.JSON)
                .get(filter + "/experience" + "?experience={experience}")
                .as(new TypeRef<List<ModelReadDto>>() {
                });

        listModels.clear();
        listModels.addAll(filterItem);
    }

    @Then("^Check if result looking is as expected: (.*)$")
    public void checkIfResultLookingIsAsExpectedExpected(String expected) {
        Assertions.assertThat(expectedResult(expected)).isEqualTo(result(listModels));
    }

    @When("^Try find created model using data examples hair color: (.*)$")
    public void tryFindCreatedModelUsingDataExamplesHairColorHAIR_COLOR(String hairColor) {

        var filterItem = RestAssured
                .given()
                .pathParam("hairColor", hairColor)
                .headers("Content-Type", ContentType.JSON)
                .get(filter + "/hairColor" + "?hairColor={hairColor}")
                .as(new TypeRef<List<ModelReadDto>>() {
                });

        listModels.clear();
        listModels.addAll(filterItem);
    }




    // Gherkin sending String "true" or "false" and it will be expected result, just change it at boolean for Assertions
    private boolean expectedResult(String expected) {
        return expected.equals("true");
    }

    // If model was find, size list is equals 1 and method return true else return false
    private boolean result(List<ModelReadDto> result) {
        return result.size() == 1;
    }

}

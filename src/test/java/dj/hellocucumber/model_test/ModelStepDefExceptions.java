package dj.hellocucumber.model_test;

import dj.exception.notFound.NotFoundException;
import dj.models.competition.model.ModelService;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelStepDefExceptions {

    @Autowired
    private ModelService modelService;

    @Then("Check if throws NotFoundException for not exist id and message is as we want")
    public void checkIfThrowsNotFoundExceptionForNotExistIdAndMessageIsAsWeWant() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            modelService.findById(-1L);
        });
        String expectedMessage = "Resource does not exist";
        String actualMessage = exception.getErrorMessage().getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}


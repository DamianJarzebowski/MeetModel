package dj.hellocucumber.domain;

import dj.repository.ModelRepository;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainStep {

    @Autowired
    private ModelRepository modelRepository;

    @Given("Delete all records models")
    public void delete_all_records_models() {
        modelRepository.deleteAll();
    }
}

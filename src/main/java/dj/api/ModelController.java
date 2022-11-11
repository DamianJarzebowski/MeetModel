package dj.api;

import dj.models.identity.competition.model.ModelService;
import dj.models.identity.competition.model.dto.ModelPersonalInformationDto;
import dj.models.identity.competition.model.dto.ModelReadDto;
import dj.models.identity.competition.model.dto.ModelWriteDto;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/{id}")
    ResponseEntity<ModelReadDto> findById(@PathVariable long id) {
            return ResponseEntity.ok(modelService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<ModelReadDto>> findAll() {
        return ResponseEntity.ok(modelService.findAll());
    }

    @PostMapping("")
    ResponseEntity<ModelReadDto> saveModel(@RequestBody ModelWriteDto dto) {
        ModelReadDto savedModel = modelService.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/general")
    ResponseEntity<ModelReadDto> updatePersonalInformation(@PathVariable long id, @RequestBody ModelPersonalInformationDto dto) {
        ModelReadDto updatedModel = modelService.updatePersonalInformation(id, dto);
        return ResponseEntity.ok(updatedModel);
    }

    @PutMapping("/{id}/skills")
    ResponseEntity<ModelReadDto> updateSkills(@PathVariable long id, @RequestBody Set<String> skills) {
        ModelReadDto updatedModel = modelService.updateSkills(id, skills);
        return ResponseEntity.ok(updatedModel);
    }

    @PutMapping("/{id}/characteristics")
    ResponseEntity<ModelReadDto> updateCharacteristics(@PathVariable long id, @RequestBody Set<String> characteristics) {
        ModelReadDto updatedModel = modelService.updateCharacteristic(id, characteristics);
        return ResponseEntity.ok(updatedModel);
    }

    @PutMapping("/{id}/achievements")
    ResponseEntity<ModelReadDto> updateAchievements(@PathVariable long id, @RequestBody Set<String> achievements) {
        ModelReadDto updatedModel = modelService.updateAchievements(id, achievements);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteModel(@PathVariable long id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

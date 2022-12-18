package dj.api;

import dj.models.competition.model.dto.ModelReadDto;
import dj.models.competition.photographer.PhotographerServiceImpl;
import dj.models.competition.photographer.dto.PhotographerPersonalInfoDto;
import dj.models.competition.photographer.dto.PhotographerReadDto;
import dj.models.competition.photographer.dto.PhotographerWriteDto;
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
@RequestMapping("/api/photographer")
public class PhotographerController {

    private final PhotographerServiceImpl photographerService;

    @GetMapping("/{id}")
    ResponseEntity<PhotographerReadDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(photographerService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<PhotographerReadDto>> findAll() {
        return ResponseEntity.ok(photographerService.findAll());
    }

    @PostMapping("")
    ResponseEntity<ModelReadDto> saveModel(@RequestBody PhotographerWriteDto dto) {
        PhotographerReadDto savedPhotographer = photographerService.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPhotographer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/general")
    ResponseEntity<PhotographerReadDto> updatePersonalInformation(@PathVariable long id, @RequestBody PhotographerPersonalInfoDto dto) {
        PhotographerReadDto updatedModel = photographerService.updatePersonalInformation(id, dto);
        return ResponseEntity.ok(updatedModel);
    }

    @PutMapping("/{id}/achievements")
    ResponseEntity<PhotographerReadDto> updateAchievements(@PathVariable long id, @RequestBody Set<String> achievements) {
        PhotographerReadDto updatedModel = photographerService.updateAchievements(id, achievements);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteModel(@PathVariable long id) {
        photographerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

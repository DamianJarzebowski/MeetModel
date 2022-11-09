package DJ.api;

import DJ.exception.notFound.NotFoundException;
import DJ.models.identity.competition.model.ModelService;
import DJ.models.identity.competition.model.dto.ModelReadDto;
import DJ.models.identity.competition.model.dto.ModelWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/{id}")
    ResponseEntity<Object> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(modelService.findById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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


}

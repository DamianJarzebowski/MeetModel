package dj.api;

import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.ModelFilter;
import dj.models.competition.model.dto.ModelReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/filter")
public class ModelFilterController {

    private final ModelFilter modelFilter;

    @GetMapping("")
    ResponseEntity<List<ModelReadDto>> filterScopeOfWork(@PathVariable ScopeOfWorkDto dto) {
        return ResponseEntity.ok(modelFilter.findModelsWithScopeOfWork(dto));
    }

}

package dj.api;

import dj.models.competition.domain.dto.AgeRangeDto;
import dj.models.competition.domain.dto.ScopeOfWorkDto;
import dj.models.competition.model.ModelFilter;
import dj.models.competition.model.dto.ModelReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/filter")
public class ModelFilterController {

    private final ModelFilter modelFilter;

    @GetMapping("/scopeOfWork")
    ResponseEntity<List<ModelReadDto>> filterScopeOfWork(@ModelAttribute ScopeOfWorkDto dto) {
        var result = modelFilter.findModelsWithScopeOfWork(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/age")
    ResponseEntity<List<ModelReadDto>> filterBetweenAge(@ModelAttribute AgeRangeDto range) {
        var result = modelFilter.findModelBetweenAge(range);
        return ResponseEntity.ok(result);
    }





}

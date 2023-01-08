package dj.api;

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
    ResponseEntity<List<ModelReadDto>> filterBetweenAge(@RequestParam int from,
                                                        @RequestParam int to) {
        var result = modelFilter.findModelBetweenAge(from, to);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/experience")
    ResponseEntity<List<ModelReadDto>> filterExperience(@RequestParam String experience) {
        var result = modelFilter.findModelsWithExperience(experience);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hairColor")
    ResponseEntity<List<ModelReadDto>> filterHairColor(@RequestParam String hairColor) {
        var result = modelFilter.findModelsWithHairColor(hairColor);
        return ResponseEntity.ok(result);
    }


}

package dj.api;

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
    ResponseEntity<List<ModelReadDto>> filterScopeOfWork(@RequestParam(required = false) Boolean fashion,
                                                         @RequestParam(required = false) Boolean portrait,
                                                         @RequestParam(required = false) Boolean glamour,
                                                         @RequestParam(required = false) Boolean act,
                                                         @RequestParam(required = false) Boolean editorial,
                                                         @RequestParam(required = false) Boolean coveredNudity,
                                                         @RequestParam(required = false) Boolean makeUpAndStylization) {
        var result = modelFilter.findModelsWithScopeOfWork(fashion, portrait, glamour, act, editorial, coveredNudity, makeUpAndStylization);
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

    @GetMapping("/naturalColor")
    ResponseEntity<List<ModelReadDto>> filterNaturalColor(@RequestParam String naturalColor) {
        var result = modelFilter.findModelsWithNaturalColor(naturalColor);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hairLength")
    ResponseEntity<List<ModelReadDto>> filterHairLength(@RequestParam String hairLength) {
        var result = modelFilter.findModelsWithHairLength(hairLength);
        return ResponseEntity.ok(result);
    }

    // Normalize param
    // Growth 140-200, Weight 35-110, Bust 75-120, Waist 55-95, Hips 80-120
    @GetMapping("/size")
    ResponseEntity<List<ModelReadDto>> filterSize(@RequestParam(required = false) Integer growthFrom, @RequestParam(required = false) Integer growthTo,
                                                  @RequestParam(required = false) Integer weightFrom, @RequestParam(required = false) Integer weightTo,
                                                  @RequestParam(required = false) Integer bustFrom, @RequestParam(required = false) Integer bustTo,
                                                  @RequestParam(required = false) Integer waistFrom, @RequestParam(required = false) Integer waistTo,
                                                  @RequestParam(required = false) Integer hipsFrom, @RequestParam(required = false) Integer hipsTo) {
        var result = modelFilter.findModelsWithLookingSizes(growthFrom, growthTo,
                                                                             weightFrom, weightTo,
                                                                             bustFrom, bustTo,
                                                                             waistFrom, waistTo,
                                                                             hipsFrom, hipsTo);
        return ResponseEntity.ok(result);
    }

}

package dj.models.identity.competition.photographer.dto;

import dj.models.identity.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class PhotographerReadDto {

    private Long id;

    private User user;

    private Set<String> achievements = new HashSet<>();

    private Set<String> skills = new HashSet<>();
}

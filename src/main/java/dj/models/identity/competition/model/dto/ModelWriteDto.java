package dj.models.identity.competition.model.dto;

import dj.models.identity.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ModelWriteDto {

    private User user;

    private Integer age;

    private Set<String> skills = new HashSet<>();

    private Set<String> achievements = new HashSet<>();

    private Set<String> characteristics = new HashSet<>();

}

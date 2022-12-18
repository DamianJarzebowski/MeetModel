package dj.models.competition.model.dto;

import dj.models.competition.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ModelWriteDto {

    private User user;

    private Set<String> achievements = new HashSet<>();

    private Set<String> characteristics = new HashSet<>();

}

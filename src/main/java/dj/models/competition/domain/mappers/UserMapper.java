package dj.models.competition.domain.mappers;

import dj.models.competition.domain.User;
import dj.models.competition.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toEntity(UserDto dto) {
        return new User()
                .setName(dto.getName())
                .setLastName(dto.getLastName())
                .setDescription(dto.getDescription())
                .setExperience(dto.getExperience())
                .setProfession(dto.getProfession())
                .setAge(dto.getAge())
                .setEmail(dto.getEmail());
    }
}

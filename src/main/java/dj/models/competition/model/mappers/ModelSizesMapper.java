package dj.models.competition.model.mappers;

import dj.models.competition.model.Model;
import dj.models.competition.model.dto.ModelSizesDto;
import org.springframework.stereotype.Service;

@Service
public class ModelSizesMapper {

    public Model.Sizes toEntity(ModelSizesDto dto) {
        return new Model.Sizes()
                .setGrowth(dto.getGrowth())
                .setWeight(dto.getWeight())
                .setBust(dto.getBust())
                .setWaist(dto.getWaist())
                .setHips(dto.getHips())
                .setHair(dto.getHair())
                .setHairColor(dto.getHairColor())
                .setNaturalColor(dto.getNaturalColor())
                .setClothesSize(dto.getClothesSize())
                .setFootwear(dto.getFootwear());
    }

}

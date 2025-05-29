package dto;

import entity.PetSex;
import entity.PetType;

public record PetFilterDTO(
        PetType type,
        PetSex sex,
        String containsInName,
        String breed,
        Double ageGreaterThan,
        Double ageLesserThan,
        Double weightGreaterThan,
        Double weightLesserThan,
        String foundCity
) {}

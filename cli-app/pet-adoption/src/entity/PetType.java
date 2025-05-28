package entity;

import exception.InvalidPetDataException;

public enum PetType {
    DOG("cachorro"),
    CAT("gato");

    private final String portugueseName;

    PetType(String portugueseName) {
        this.portugueseName = portugueseName;
    }

    public static PetType fromPortuguese(String type){
        String normalizedInput = type.toLowerCase().trim();

        for(PetType petType : PetType.values()){
            if(petType.portugueseName.equals(normalizedInput) ||
                petType.name().equalsIgnoreCase(normalizedInput)){
                return petType;
            }
        }

        throw new InvalidPetDataException("Tipo de pet inválido: "+type);
    }
}

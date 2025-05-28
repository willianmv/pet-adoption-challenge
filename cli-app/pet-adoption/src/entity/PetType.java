package entity;

import exception.InvalidPetDataException;

public enum PetType {
    DOG("Cachorro"),
    CAT("Gato");

    private final String portugueseName;

    PetType(String portugueseName) {
        this.portugueseName = portugueseName;
    }

    public static PetType fromPortuguese(String type){
        String normalizedInput = type.toLowerCase().trim();

        for(PetType petType : PetType.values()){
            if(petType.portugueseName.equalsIgnoreCase(normalizedInput) ||
                petType.name().equalsIgnoreCase(normalizedInput)){
                return petType;
            }
        }

        throw new InvalidPetDataException("Tipo de pet inválido: "+type);
    }

    @Override
    public String toString() {
        return this.portugueseName;
    }
}

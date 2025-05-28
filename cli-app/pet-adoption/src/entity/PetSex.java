package entity;

import exception.InvalidPetDataException;

public enum PetSex {
    MALE("Masculino"),
    FEMALE("Feminino");

    private final String portugueseName;

    PetSex(String portugueseName) {
        this.portugueseName = portugueseName;
    }

    public String getPortugueseName() {
        return portugueseName;
    }

    public static PetSex fromPortuguese(String value){
        String normalizedValue = value.toLowerCase().trim();

        for(PetSex sex : PetSex.values()){
            if(sex.name().equalsIgnoreCase(normalizedValue) ||
                sex.portugueseName.equals(normalizedValue)){
                return sex;
            }
        }

        throw new InvalidPetDataException("Sexo do pet inválido: "+value);
    }

    @Override
    public String toString() {
        return this.portugueseName;
    }
}

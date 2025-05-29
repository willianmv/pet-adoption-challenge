package dao.spec;

import entity.Pet;
import entity.PetSex;
import entity.PetType;

public class PetSpecs{

    public static Specification<Pet> containsIsString(String text){
        return pet -> pet.getFullName().toLowerCase().contains(text.toLowerCase());
    }

    public static Specification<Pet> hasType(PetType type){
        return pet -> pet.getType().equals(type);
    }

    public static Specification<Pet> hasSex(PetSex sex){
        return pet -> pet.getSex().equals(sex);
    }

    public static Specification<Pet> hasBreed(String breed){
        return pet -> pet.getBreed().equalsIgnoreCase(breed);
    }

    public static Specification<Pet> hasAgeLesserThan(double age){
        return pet -> pet.getAge() <= age;
    }

    public static Specification<Pet> hasAgeGreaterThan(double age){
        return pet -> pet.getAge() >= age;
    }

    public static Specification<Pet> hasWeightLesserThan(double weight){
        return pet -> pet.getWeight() <= weight;
    }

    public static Specification<Pet> hasWeightGreaterThan(double weight){
        return pet -> pet.getWeight() >= weight;
    }

    public static Specification<Pet> foundIn(String cityName){
        return pet -> pet.getAddress().getCity().equalsIgnoreCase(cityName);
    }


}

package utils;

import exception.InvalidPetDataException;

public class PetValidator {

    public static String validatePetStringFields(String name){
        if(!name.matches("^[A-Za-zÀ-ÿ\\s]+$")){
            throw new InvalidPetDataException("O campo só pode conter letras.");
        }
        return name.trim();
    }

    public static double validateWeight(double weight){
        if(weight < 0.5 || weight > 60){
            throw new InvalidPetDataException("O peso deve estar entre 0.5kg e 60kg.");
        }
        return weight;
    }

    public static double validateAge(double age){
        if(age > 20){
            throw new InvalidPetDataException("A idade não pode ser maior que 20 anos.");
        }
        return age;
    }

}

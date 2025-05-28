package utils;

import exception.InvalidPetDataException;

public class PetValidator {

    public static String validatePetStringFields(String name){
        if(!name.matches("^[A-Za-zÀ-ÿ\\s]+$")){
            throw new InvalidPetDataException("O CAMPO SÓ PODE CONTER LETRAS.");
        }
        return name.trim();
    }

    public static double validateWeight(double weight){
        if(weight < 0.5 || weight > 60){
            throw new InvalidPetDataException("O PESO DEVE SER ENTRE 0.5kg E 60kg.");
        }
        return weight;
    }

    public static double validateAge(double age){
        if(age > 20){
            throw new InvalidPetDataException("A IDADE NÃO PODE SER MAIOR DE 20 ANOS.");
        }
        return age;
    }

}

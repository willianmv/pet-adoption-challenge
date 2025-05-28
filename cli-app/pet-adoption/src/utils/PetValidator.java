package utils;

import exception.InvalidPetDataException;

public class PetValidator {

    public static String validatePetFullName(String fullName) {
        if (!fullName.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            throw new InvalidPetDataException("O NOME COMPLETO SÓ PODE CONTER LETRAS E ESPAÇOS.");
        }

        String[] partes = fullName.trim().split("\\s+");
        if (partes.length < 2) {
            throw new InvalidPetDataException("INSIRA PELO MENOS NOME E SOBRENOME.");
        }

        return fullName.trim();
    }

    public static String validatePetStringFields(String text){
        if(!text.matches("^[A-Za-zÀ-ÿ\\s]+$")){
            throw new InvalidPetDataException("O CAMPO SÓ PODE CONTER LETRAS.");
        }
        return text.trim();
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

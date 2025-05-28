package service;

import entity.Address;
import entity.Pet;
import entity.PetSex;
import entity.PetType;
import utils.PetValidator;

import static utils.InputUtils.*;

public class PetService {

    public void addPet() {
        Pet createdPet = getPetData();
        System.out.println(createdPet);
    }

    private Pet getPetData() {

        String petName = readAndValidatedValue(
                () -> readNotEmptyString("--INSIRA O NOME DO PET:"),
                PetValidator::validatePetStringFields);

        String petLastName = readAndValidatedValue(
                () -> readNotEmptyString("--INSIRA O SOBRENOME DO PET:"),
                PetValidator::validatePetStringFields);

        PetType type = readAndTransformValue(
                () -> readNotEmptyString("--INSIRA O TIPO DO PET (Cachorro ou Gato):"),
                PetType::fromPortuguese);

        String breed = readAndValidatedValue(
                () -> readNotEmptyString("--INSIRA A RAÇA DO PET:"),
                PetValidator::validatePetStringFields);

        PetSex sex = readAndTransformValue(
                () -> readNotEmptyString("--INSIRA O SEXO DO PET (Masculino ou Feminino):"),
                PetSex::fromPortuguese);

        String city = readAndValidatedValue(
                () -> readNotEmptyString("--INSIRA A CIDADE ONDE O PET FOI ENCONTRADO:"),
                PetValidator::validatePetStringFields);

        String street = readAndValidatedValue(
                () -> readNotEmptyString("--INSIRA A RUA ONDE O PET FOI ENCONTRADO:"),
                PetValidator::validatePetStringFields);

        String number = readNotEmptyString("--INSIRA A O NUMERO DA CASA ONDE O PET FOI ENCONTRADO:");

        double weight = readAndValidatedValue(
                () -> readDoubleValue("--INSIRA O PESO APROXIMADO DO PET:"),
                PetValidator::validateWeight);

        double age = readAndValidatedValue(
                () -> readDoubleValue("--INSIRA A IDADE APROXIMADA DO PET:"),
                PetValidator::validateAge);

        return new Pet(
                petName, petLastName,
                type, sex, breed,
                new Address(city, number, street),
                age, weight);
    }
}

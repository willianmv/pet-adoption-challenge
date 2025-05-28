package service;

import entity.Address;
import entity.Pet;
import entity.PetSex;
import entity.PetType;
import exception.BusinessException;
import utils.InputUtils;

import static utils.PetValidator.*;

public class PetService {

    public void addPet() {
        Pet createdPet = getPetData();
        System.out.println(createdPet);
    }

    private Pet getPetData() {
        while (true){

            try{
                String petName = validatePetStringFields(
                        InputUtils.readNotEmptyString("--INSIRA O NOME DO PET:"));

                String petLastName = validatePetStringFields(
                        InputUtils.readNotEmptyString("--INSIRA O SOBRENOME DO PET:"));

                PetType type = entity.PetType.fromPortuguese(
                        InputUtils.readNotEmptyString("--INSIRA O TIPO DO PET (Cachorro ou Gato):"));

                String breed = validatePetStringFields(
                        InputUtils.readNotEmptyString("--INSIRA A RAÇA DO PET:"));

                PetSex sex = PetSex.fromPortuguese(
                        InputUtils.readNotEmptyString("--INSIRA O SEXO DO PET (Masculino ou Feminino):"));

                String city = validatePetStringFields(
                        InputUtils.readNotEmptyString("--INSIRA A CIDADE ONDE O PET FOI ENCONTRADO:"));

                String street = validatePetStringFields(
                        InputUtils.readNotEmptyString("--INSIRA A RUA ONDE O PET FOI ENCONTRADO:"));

                String number = InputUtils.readNotEmptyString("--INSIRA A O NUMERO DA CASA ONDE O PET FOI ENCONTRADO:");

                double weight = validateWeight(InputUtils.readDoubleValue("--INSIRA O PESO APROXIMADO DO PET:"));

                double age = validateAge(InputUtils.readDoubleValue("--INSIRA A IDADE APROXIMADA DO PET:"));

                return new Pet(
                        petName, petLastName,
                        type, sex, breed,
                        new Address(city, number, street),
                        age, weight);

            }catch (BusinessException ex){
                System.out.println(ex.getMessage());
                System.out.println("--POR FAVOR< PREENCHA OS DADOS CORRETAMENTE.\n");
            }

        }
    }
}

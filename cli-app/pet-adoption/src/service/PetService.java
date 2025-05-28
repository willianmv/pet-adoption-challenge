package service;

import entity.*;
import utils.PetValidator;

import java.util.List;

import static utils.InputUtils.*;

public class PetService {

    private final FormQuestionService formQuestionService;

    public PetService(FormQuestionService formQuestionService) {
        this.formQuestionService = formQuestionService;
    }

    public void addPet() {
        List<FormQuestion>  questions = formQuestionService.loadFormQuestions();
        Pet createdPet = getPetData(questions);
        System.out.println(createdPet);
    }

    private Pet getPetData(List<FormQuestion> questions) {

        String petFullName = readAndValidatedValue(
                () -> readNotEmptyString(questions.getFirst().getNumber()+" - "+questions.getFirst().getText()),
                PetValidator::validatePetFullName);

        PetType type = readAndTransformValue(
                () -> readNotEmptyString(questions.get(1).getNumber()+" - "+questions.get(1).getText()),
                PetType::fromPortuguese);

        PetSex sex = readAndTransformValue(
                () -> readNotEmptyString(questions.get(2).getNumber()+" - "+questions.get(2).getText()),
                PetSex::fromPortuguese);

        System.out.println(questions.get(3).getNumber()+" - "+questions.get(3).getText());
        String city = readAndValidatedValue(
                () -> readNotEmptyString("--(CIDADE)"),
                PetValidator::validatePetStringFields);

        String street = readAndValidatedValue(
                () -> readNotEmptyString("--(RUA)"),
                PetValidator::validatePetStringFields);

        String number = readNotEmptyString("--(NUMERO DA CASA)");

        double age = readAndValidatedValue(
                () -> readDoubleValue(questions.get(4).getNumber()+" - "+questions.get(4).getText()),
                PetValidator::validateAge);

        double weight = readAndValidatedValue(
                () -> readDoubleValue(questions.get(5).getNumber()+" - "+questions.get(5).getText()),
                PetValidator::validateWeight);

        String breed = readAndValidatedValue(
                () -> readNotEmptyString(questions.get(6).getNumber()+" - "+questions.get(6).getText()),
                PetValidator::validatePetStringFields);

        return new Pet(
                petFullName,
                type, sex, breed,
                new Address(city, number, street),
                age, weight);
    }
}

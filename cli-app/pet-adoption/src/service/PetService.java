package service;

import entity.*;
import utils.PetValidatorUtils;
import dao.PetDAO;
import dao.QuestionDAO;

import java.time.LocalDateTime;
import java.util.List;

import static utils.InputUtils.*;

public class PetService {

    private final QuestionDAO questionFileDAO;
    private final PetDAO petFileDAO;
    private final String loadQuestionFile;
    private final String petDataBaseFile;

    public PetService(QuestionDAO questionFileDAO, String loadQuestionFile,
                      PetDAO petFileDAO, String petDataBaseFile) {

        this.questionFileDAO = questionFileDAO;
        this.petFileDAO = petFileDAO;
        this.loadQuestionFile = loadQuestionFile;
        this.petDataBaseFile = petDataBaseFile;
    }

    public Pet addPet() {
        List<Question>  questions = questionFileDAO.loadFromFile(loadQuestionFile);
        Pet createdPet = getPetData(questions);
        createdPet.setCreatedAt(LocalDateTime.now());
        petFileDAO.saveFile(createdPet, petDataBaseFile);
        return createdPet;
    }

    public List<Pet> getAllPets() {
        return petFileDAO.loadFromFile(petDataBaseFile);
    }

    private Pet getPetData(List<Question> questions) {

        String petFullName = readAndValidatedValue(
                () -> readNotEmptyString(questions.getFirst().getNumber()+" - "+questions.getFirst().getText()),
                PetValidatorUtils::validatePetFullName);

        PetType type = readAndTransformValue(
                () -> readNotEmptyString(questions.get(1).getNumber()+" - "+questions.get(1).getText()),
                PetType::fromPortuguese);

        PetSex sex = readAndTransformValue(
                () -> readNotEmptyString(questions.get(2).getNumber()+" - "+questions.get(2).getText()),
                PetSex::fromPortuguese);

        System.out.println(questions.get(3).getNumber()+" - "+questions.get(3).getText());
        String city = readAndValidatedValue(
                () -> readNotEmptyString("--(CIDADE)"),
                PetValidatorUtils::validatePetStringFields);

        String street = readAndValidatedValue(
                () -> readNotEmptyString("--(RUA)"),
                PetValidatorUtils::validatePetStringFields);

        String number = readNotEmptyString("--(NUMERO DA CASA)");

        double age = readAndValidatedValue(
                () -> readDoubleValue(questions.get(4).getNumber()+" - "+questions.get(4).getText()),
                PetValidatorUtils::validateAge);

        double weight = readAndValidatedValue(
                () -> readDoubleValue(questions.get(5).getNumber()+" - "+questions.get(5).getText()),
                PetValidatorUtils::validateWeight);

        String breed = readAndValidatedValue(
                () -> readNotEmptyString(questions.get(6).getNumber()+" - "+questions.get(6).getText()),
                PetValidatorUtils::validatePetStringFields);

        return new Pet(
                petFullName,
                type, sex, breed,
                new Address(city, number, street),
                age, weight);
    }
}

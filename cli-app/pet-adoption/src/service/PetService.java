package service;

import dao.spec.Specification;
import dto.PetFilterDTO;
import entity.*;
import utils.PetValidatorUtils;
import dao.PetDAO;
import dao.QuestionDAO;

import java.time.LocalDateTime;
import java.util.List;

import static dao.spec.PetSpecs.*;
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

    public List<Pet> getPetsByFilters() {
        return filteredPets();
    }

    private List<Pet> filteredPets() {
        Specification<Pet> petSpec = getSpecs();
        return getAllPets()
                .stream()
                .filter(petSpec::isSatisfiedBy)
                .toList();
    }

    private Specification<Pet> getSpecs() {
        PetFilterDTO filters = getFiltersFromUser();

        Specification<Pet> spec = Specification.alwaysTrue();
        spec = spec.and(hasType(filters.type()));

        if(filters.sex() != null){
            spec = spec.and(hasSex(filters.sex()));
        }

        if(filters.containsInName() != null){
            spec = spec.and(containsIsString(filters.containsInName()));
        }

        if(filters.breed() != null){
            spec = spec.and(hasBreed(filters.breed()));
        }

        if(filters.ageLesserThan() != null){
            spec = spec.and(hasAgeLesserThan(filters.ageLesserThan()));
        }

        if(filters.ageGreaterThan() != null){
            spec = spec.and(hasAgeGreaterThan(filters.ageGreaterThan()));
        }

        if(filters.weightLesserThan() != null){
            spec = spec.and(hasWeightLesserThan(filters.weightLesserThan()));
        }

        if(filters.weightGreaterThan() != null){
            spec = spec.and(hasWeightGreaterThan(filters.weightGreaterThan()));
        }

        if(filters.foundCity() != null){
            spec  = spec.and(foundIn(filters.foundCity()));
        }

        return spec;
    }

    private PetFilterDTO getFiltersFromUser() {
        PetType type = readAndTransformValue(
                () -> readNotEmptyString("--INSIRA O TIPO DO PET: (CACHORRO/GATO)"),
                PetType::fromPortuguese);

        PetSex sex = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR SEXO? (s/n)").equalsIgnoreCase("s")){
            sex = readAndTransformValue(
                    () -> readNotEmptyString("--INSIRA O SEXO DO PET: (MASCULINO/FEMININO)"),
                    PetSex::fromPortuguese);
        }

        String name = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR NOME? (s/n)").equalsIgnoreCase("s")){
            name = readNotEmptyString("--INSIRA A PARTE DO NOME: ");
        }

        String breed = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR RAÇA? (s/n)").equalsIgnoreCase("s")){
            breed = readNotEmptyString("--INSIRA A RAÇA: ");
        }

        Double ageGreaterThan = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR IDADE MAIOR QUE? (s/n)").equalsIgnoreCase("s")){
            ageGreaterThan = readAndValidatedValue(
                    () -> readDoubleValue("--INSIRA A IDADE: "),
                    PetValidatorUtils::validateAge);
        }

        Double ageLesserThan = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR IDADE MENOR QUE? (s/n)").equalsIgnoreCase("s")){
            ageLesserThan = readAndValidatedValue(
                    () -> readDoubleValue("--INSIRA A IDADE: "),
                    PetValidatorUtils::validateAge);
        }

        Double weightGreaterThan = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR PESO MAIOR QUE? (s/n)").equalsIgnoreCase("s")){
            weightGreaterThan = readAndValidatedValue(
                    () -> readDoubleValue("--INSIRA O PESO: "),
                    PetValidatorUtils::validateWeight);
        }

        Double weightLesserThan = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR PESO MENOR QUE? (s/n)").equalsIgnoreCase("s")){
            weightLesserThan = readAndValidatedValue(
                    () -> readDoubleValue("--INSIRA O PESO: "),
                    PetValidatorUtils::validateWeight);
        }

        String foundInCity = null;
        if(readNotEmptyString("--DESEJA FILTRAR POR CIDADE EM QUE FOI ENCONTRADO? (s/n)").equalsIgnoreCase("s")){
            foundInCity = readNotEmptyString("--INSIRA O NOME DA CIDADE: ");
        }

        return new PetFilterDTO(type, sex, name, breed, ageGreaterThan, ageLesserThan,
                weightGreaterThan, weightLesserThan, foundInCity);
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

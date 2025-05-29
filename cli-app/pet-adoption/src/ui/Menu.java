package ui;

import entity.Pet;
import service.PetService;
import utils.InputUtils;

import java.util.List;

public class Menu {

    private final PetService petService;

    public Menu(PetService petService) {
        this.petService = petService;
    }

    public void showPetMenu(){

        String petMenu = """
                ======================== PET MENU =============================
                1. Cadastrar um novo pet
                2. Listar todos os pets cadastrados
                3. Listar pets por algum critério
                4. Alterar os dados do pet cadastrado
                5. Deletar um pet cadastrado
                6. Sair
                ===============================================================
                \n--DIGITE A OPÇÃO DESEJADA:
                """;

        while(true){
            int op = InputUtils.readIntValue(petMenu);
            switch (op){
                case 1:
                    addNewPet();
                    break;
                case 2:
                    listPets();
                    break;
                case 3:
                    listByFilters();
                    break;
                case 4:
                    updatePetData();
                    break;
                case 5:
                    deletePet();
                    break;
                case 6:
                    System.out.println("ENCERRANDO...");
                    return;
                default:
                    System.out.println("INSIRA UMA OPÇÃO VÁLIDA");
            }
        }
    }

    private void addNewPet() {
        Pet pet = petService.addPet();
        if(pet != null){
            System.out.println("PET FOI CADASTRADO");
            System.out.println(pet);
        }
    }

    private void listPets() {
        List<Pet> pets = petService.getAllPets();
        if(!pets.isEmpty()) pets.forEach(System.out::println);
    }

    private void listByFilters() {}

    private void updatePetData() {}

    private void deletePet() {}

}

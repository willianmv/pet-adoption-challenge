package ui;

import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    public void showPetMenu(){

        String petMenu = """
                ======================== PET MENU =============================
                1. Cadastrar um novo pet
                2. Alterar os dados do pet cadastrado
                3. Deletar um pet cadastrado
                4. Listar todos os pets cadastrados
                5. Listar pets por algum critério:
                6. Sair
                ===============================================================
                """;

        while(true){
            System.out.println(petMenu);
            System.out.print("INSIRA A OPÇÃO DESEJADA: ");
            int op = sc.nextInt();
            switch (op){
                case 1:
                    addNewPet();
                    break;
                case 2:
                    updatePetData();
                    break;
                case 3:
                    deletePet();
                    break;
                case 4:
                    listPets();
                    break;
                case 5:
                    listByFilters();
                    break;
                case 6:
                    System.out.println("ENCERRANDO...");
                    return;
                default:
                    System.out.println("INSIRA UMA OPÇÃO VÁLIDA");
            }
        }

    }

    private void addNewPet() {}

    private void updatePetData() {}

    private void deletePet() {}

    private void listPets() {}

    private void listByFilters() {}

}

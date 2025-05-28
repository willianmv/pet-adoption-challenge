import service.FormQuestionService;
import service.PetFileService;
import service.PetService;
import ui.Menu;

public class App {

    public static void main(String[] args) {
        FormQuestionService formQuestionService = new FormQuestionService("app-docs/forms/formulario.txt");
        PetFileService petFileService = new PetFileService("app-docs/db");
        PetService petService = new PetService(formQuestionService, petFileService);
        Menu menu = new Menu(petService);
        menu.showPetMenu();
    }

}

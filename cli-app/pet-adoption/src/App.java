import dao.QuestionDAO;
import dao.PetDAO;
import service.PetService;
import ui.Menu;

public class App {

    public static void main(String[] args) {

        QuestionDAO questionFileDAO = new QuestionDAO();
        PetDAO petFileDAO = new PetDAO();

        PetService petService = new PetService(
                questionFileDAO, "app-docs/forms/formulario.txt",
                petFileDAO, "app-docs/db");

        Menu menu = new Menu(petService);
        menu.showPetMenu();
    }

}

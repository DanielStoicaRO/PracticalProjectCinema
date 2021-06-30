package cinema_project;

import cinema_project.MainMenu.MainMenu;
import cinema_project.Service.DataBaseService;

public class Main {

    public static void main(String[] args) {

        new DataBaseService().getSessionFactory();
        MainMenu mainMenu = new MainMenu();

//        MethodClass.manualInsertMovie();
//        MethodClass.manualInsertMovieType();

    }
}





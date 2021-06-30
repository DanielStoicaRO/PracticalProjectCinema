package cinema_project;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public MainMenu() {
        showMainMenu();
    }

    private final Scanner scanner = new Scanner(System.in);
    private final Scanner scannerInt = new Scanner(System.in);

    String user_name;
    String password;
    String phoneNumber;

    final int NUMBEROFSEATS = 20;
    final int TICKETPRICE = 20;

    private void showMainMenu() {
        int option = 0;
        System.out.println(" Welcome to CINEMA menu:" +
                "\nPlease select your option:" +
                "\n1. Register New User" +
                "\n2. Login Existing User" +
                "\n3. ADMIN Login");
        try {
            option = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Please introduce just number!");
        }
        switch (option) {
            case 1:
                showMainMenuCase1RegisterNewUser();
                break;
            case 2:
                showMainMenuCase2LoginExistingUser();
                break;
            case 3:
                showMainMenuCase3LoginAdmin();
                break;
            default:
                System.out.println("The Option is not valid");
                showMainMenu();
        }
    }
    private void showMainMenuCase1RegisterNewUser() {

            System.out.println("Register New: Type User_Name");
            user_name = scanner.next();
            System.out.println("Register New: Type Password");
            password = scanner.next();
            System.out.println("Register New: Type Phone_Number");
            phoneNumber = scanner.next();
            MethodClass.registerNewUser(user_name, password, phoneNumber);
            showMainMenu();

    }
    private void showMainMenuCase2LoginExistingUser() {
        System.out.println("Login Existing: Type User_Name: ");
        user_name = scanner.next();
        System.out.println("Login Existing: Type User_Password");
        password = scanner.next();
        try {
            MethodClass.loginUser(user_name, password);
            System.out.println("Login successfully !");
            showCinemaMenu();
        } catch (Exception e) {
            System.out.println("Login failed!");
            showMainMenu();
        }
    }
    private void showMainMenuCase3LoginAdmin() {
        System.out.println("Login ADMIN: Type User_Name: ");
        user_name = scanner.next();
        System.out.println("Login ADMIN: Type User_Password");
        password = scanner.next();
        try {
            MethodClass.loginAdminUser(user_name, password);
            System.out.println("Login successfully !");
            showAdminMenu();
        } catch (Exception e) {
            System.out.println("Login failed!");
            showAdminMenu();
        }
    }

    private void showCinemaMenu() {
        System.out.println("\"Please select: " +
                "\n 1.Movie Program " +
                "\n 2.Buy Tickets " +
                "\n 0.Exit to Main Menu \" ");
        int option = scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    MethodClass.showMovie();
                    showCinemaMenu();
                    int index = scanner.nextInt();
                    break;
                case 2:
                    showCinemaMenuCase2BuyTickets();
                    break;
            }
            System.out.println("Select another options!");
            option = scanner.nextInt();
        }
        showMainMenu();
    }
    private void showCinemaMenuCase2BuyTickets() {
        System.out.println("Buy Tickets for Movie No.: ");
        int selectMovieNumber = scanner.nextInt();
        MoviesEntity moviesEntity = new MethodClass().findMovie(selectMovieNumber);
        System.out.println(" Movie Selected: " + moviesEntity.getMovie_title()
                + " " + moviesEntity.getMovie_schedule());
        try {
            // cate tichete mai sunt disponibile /film
            List<TicketsEntity> ticketsEntity = new MethodClass().findTicketNumber(selectMovieNumber);

            //update pe numarul de locuri ramase in sala ???
            if (NUMBEROFSEATS > ticketsEntity.size()) {
                int availableSeats = NUMBEROFSEATS - ticketsEntity.size();
                System.out.println("Tickets available for movie: " + availableSeats);

                System.out.println("How many tickets you want to buy? ");
                int buyTicket = scannerInt.nextInt();

                if (buyTicket > 1) {
                    System.out.println("The price for " + buyTicket + " tickets is: " + (buyTicket * TICKETPRICE) + " Lei");
                } else {
                    System.out.println("The price for your ticket is: " + TICKETPRICE + " Lei");
                }

                if (availableSeats < buyTicket) {
                    System.out.println("Out of Tickets, chose another option");
                    showCinemaMenu();
                }

                for (int i = 0; i < buyTicket; i++) {
                    MethodClass.insertTickets(selectMovieNumber);
                }
            } else {
                System.out.println("Out of Tickets, chose another option");
                showCinemaMenu();
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    private void showAdminMenu() {
        System.out.println("\"Please select: " +
                "\n 1.Insert Movie " +
                "\n 2.Delete Movie " +
                "\n 0.Exit to Main Menu ");

        int option = 0;

        try {
            option = scannerInt.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please introduce just number!");
        }

        switch (option) {
            case 1:
                try {
                    showAdminMenuCase1InsertMovie();
                    break;
                } catch (Exception e) {
                    System.out.println("Something goes wrong : " + e);
                }
            case 2:
                showAdminMenuCase2DeleteMovie();
            case 0:
                System.out.println("Exit from Admin Menu");
                showMainMenu();
        }
    }
    private void showAdminMenuCase1InsertMovie() {
        System.out.println("Insert Movie - String Movie_Title: ");
        scanner.nextLine();
        String movie_title = scanner.nextLine();

        System.out.println("Insert Movie - String Movie_Schedule: ");
        String movie_schedule = scanner.next();

        System.out.println("Insert Movie - String Movie Type: ");
        String movieType = scanner.next();

        System.out.println("Insert Movie - Integer Movie_Duration: ");
        Integer movie_duration = scannerInt.nextInt();

        MethodClass.insertMovie(movie_title, movie_duration, movie_schedule, movieType);
        showAdminMenu();
    }
    private void showAdminMenuCase2DeleteMovie() {
        System.out.println("LIST OF MOVIE AVAILABLE:  ");
        MethodClass.showMovie();
        System.out.println("Insert Movie Number to Delete : ");
        int movie_number = scannerInt.nextInt();
        MethodClass.deleteMovieFromList(movie_number);
        System.out.println("Movie successfully delete from schedule list");

        showAdminMenu();
    }
}


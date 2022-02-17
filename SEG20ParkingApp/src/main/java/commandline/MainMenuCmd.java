package commandline;

import controller.AdminController;
import controller.UserRentController;
import controller.UserRentOutController;
import controller.UserReservationsController;
import model.User;
import repository.ParkingRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuCmd {

    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;
    private final UserRentOutCmd userRentOutCmd;

    User testUser = new User(99999999, new ArrayList<>(), new ArrayList<>());

    public MainMenuCmd(ParkingRepository parkingRepository, UserRepository userRepository) {
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;

        UserRentOutController uroc = new UserRentOutController();
        userRentOutCmd = new UserRentOutCmd(uroc);
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------");
        System.out.println("                   - Log in Menu -                     ");
        System.out.println("-------------------------------------------------------");

            System.out.println("\t1. Administrator");
            System.out.println("\t2. User");
            System.out.println("\t3. Exit");

            System.out.print("\n\tSelect menu: ");
            int loginOption = scanner.nextInt();


            switch (loginOption) {
                case 1:
                    adminMenu();
                    break;

                case 2:
                    userMenu();
                    break;

                case 3:
                    System.out.println("\n\tExiting program.");
                    System.exit(1);
                    break;

                default:
                    System.out.println("\tNot a valid option, please try again.");
                    mainMenu();
                    break;
            }
    } // end mainMenu()



    private void adminMenu() {

        AdminController adminController = new AdminController(userRepository, parkingRepository);
        AdminCmd adminCmd = new AdminCmd(adminController);

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------");
        System.out.println("                    - Admin Menu -                     ");
        System.out.println("-------------------------------------------------------");

        System.out.println("\t1. Verify parking spots");
        System.out.println("\t2. Delete parking spots");
        System.out.println("\t3. Delete users");
        System.out.println("\t4. Log out");
        System.out.print("\n\tChoose an option: ");

        int adminOption = scanner.nextInt();


        switch(adminOption){
            case 1:
                adminCmd.verifyParkingSpots();
                adminMenu();
                break;

            case 2:
                adminCmd.deleteParkingSpots();
                adminMenu();
                break;

            case 3:
                adminCmd.administrateUser();
                adminMenu();
                break;

            case 4:
                mainMenu();
                break;

            default:
                System.out.println("\tNot a valid option, please try again.");
                adminMenu();
        }

    } //end adminMenu


    public void userMenu() {

        UserRentController userRentController = new UserRentController(parkingRepository);
        UserReservationsController userReservationsController = new UserReservationsController();

        UserReservationsCmd userReservationsCmd = new UserReservationsCmd(userReservationsController, testUser);


            System.out.println("-------------------------------------------------------");
            System.out.println("                     - User Menu -                     ");
            System.out.println("-------------------------------------------------------");

            System.out.println("\t1. Find parking spot");
            System.out.println("\t2. List Reservations");
            System.out.println("\t3. Remove Reservations");
            System.out.println("\t4. Rent out parking spot");
            System.out.println("\t5. Log out");
            System.out.println("\t6. Exit");

            System.out.print("\n\tChoose menu: ");

            Scanner scanner = new Scanner(System.in);
            int userOption = scanner.nextInt();


            switch (userOption) {
                case 1:
                    UserRentCmd userRentCmd = new UserRentCmd(userRentController, testUser);
                    userRentCmd.searchForParkingSpotMenu();

                    userMenu();
                    break;

                case 2:
                    userReservationsCmd.listReservations();
                    userMenu();
                    break;

                case 3:
                    userReservationsCmd.deleteReservation();
                    userMenu();
                    break;

                case 4:
                    userRentOutCmd.rentOutMenu();
                    //System.out.println("\tRent out not implemented yet.");
                    userMenu();
                    break;

                case 5:
                    mainMenu();
                    break;

                case 6:
                    System.out.println("\n\tExiting program");
                    System.exit(1);
                    break;

                default:
                    System.out.println("\tCommand not recognized, please try again.");
                    userMenu();
                    break;
            }
    } // end userMenu()

} // end CommandLine class

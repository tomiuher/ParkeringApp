package commandline;

import controller.UserRentOutController;
import model.ParkingSpot;
import utility.SharedUtilities;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRentOutCmd {

    private final UserRentOutController uc;

    public UserRentOutCmd(UserRentOutController uc){
        this.uc = uc;
    }

    public void rentOutMenu(){

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("               - Rent out Parking Spot -               ");
        System.out.println  ("-------------------------------------------------------");

        System.out.println("\t1. Rent out a parking spot");
        System.out.println("\t2. Register another parking spot");
        System.out.println("\t3. Stop renting out a parking spot");
        System.out.println("\t4. Back to user menu\n");

        int input = SharedUtilities.getIntInput();

        switch(input){
            case 1:
                rentOutParkingSpot();
                break;
            case 2:
                registerParkingSpot();
                break;

            case 3:
                stopRentingOutParkingSpot();
                break;

            case 4:
                return;

            default:
                System.out.println("\tNot a valid option, try again");
                rentOutMenu();
        }
    }

    public void rentOutParkingSpot(){

        System.out.println("\tYour parking spots: ");

        ArrayList<ParkingSpot> list = uc.getMyParkingSpots();
        SharedUtilities.printNumberedList(list);


        System.out.print("\n\tChoose a parking spot to rent out: ");
        int input = SharedUtilities.getIntInput();
            if(input == list.size() + 1) {
                return;
            }

        uc.rentOutParkingSpot(input);
        System.out.println("\n\tYou have these parking spots rented out:");
        System.out.println("\t" + uc.getRentedOutList());

        rentOutMenu();
    }

    public void stopRentingOutParkingSpot(){
        ArrayList<ParkingSpot> list = uc.getRentedOutList();

        SharedUtilities.printNumberedList(list);

        int input = SharedUtilities.getIntInput();

        uc.removeFromRentedOutList(input);

        rentOutMenu();
    }

    public void registerParkingSpot(){

        System.out.print("\tEnter the address (any string will do): ");
        String address = SharedUtilities.getStringInput();

        System.out.print("\tEnter the city (any string will do): ");
        String city = SharedUtilities.getStringInput();

        System.out.print("\tEnter the area code (any string will do): ");
        String areaCode = SharedUtilities.getStringInput();

        uc.addParkingSpot(new ParkingSpot(address, city, areaCode, false, new HashMap<>()));

        System.out.println("\n\tParking Spot registered.");

        rentOutMenu();
    }
}

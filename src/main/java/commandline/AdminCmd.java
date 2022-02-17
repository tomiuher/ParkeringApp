package commandline;

import controller.AdminController;
import model.ParkingSpot;
import model.User;
import utility.SharedUtilities;

import java.util.ArrayList;

public class AdminCmd {

    private final AdminController adminController;

    public AdminCmd(AdminController adminController) {
        this.adminController = adminController;
    }

    private int chooseUser() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("                     - Edit User -                     ");
        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("\t   #ID\t\t\tPhone #\t\t\t\tEmail");
        ArrayList<User> userList = adminController.getUserList();
        SharedUtilities.printNumberedList(userList);

        int selectedUserId = 0;

        while(selectedUserId == 0){
            int input = SharedUtilities.getIntInput();

            if (input == userList.size() + 1) {
                return 0;
            }

            selectedUserId = adminController.getUserId(input);
        }

        System.out.println("\n\tThe selected user's info:");
        System.out.println  ("\t#ID\t\t\tPhone #\t\t\t\tEmail");
        System.out.println("\t" + adminController.getUser(selectedUserId).toString());

        return selectedUserId;
    }

    public void administrateUser(){

        int selectedUserId = -1;

        selectedUserId = chooseUser();
            if(selectedUserId == 0) {
                return;
            }

        System.out.println("\n\t1. Update info");
        System.out.println("\t2. Delete user");
        System.out.println("\t3. Back");

        int userAction = SharedUtilities.getIntInput();

        switch(userAction){
            case 1:
                System.out.println("\tNot yet implemented");
                if(selectedUserId == -1)
                    administrateUser();
                else
                    break;

            case 2:
                adminController.deleteUser(selectedUserId);
                break;

            case 3:
                return;

            default:
                System.out.println("\tInvalid option, try again");
                administrateUser();
        }

    } // end administrateUser();


    // --------------- Parking spot functions ---------------

    public void verifyParkingSpots() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("                - Verify Parking Spot -                ");
        System.out.println  ("-------------------------------------------------------");

        ArrayList<ParkingSpot> unverifiedList = adminController.getUnverifiedParkingSpots();
            if(SharedUtilities.arrayIsEmpty(unverifiedList)) {
                System.out.println("\n\tNo parking spots to verify.\n");
                return;
            }
            else {
                SharedUtilities.printNumberedList(unverifiedList);
            }

        int input = SharedUtilities.getIntInput();
            if (input == unverifiedList.size() + 1) {
                return;
            }

        for(int i = 0; i <= unverifiedList.size(); i++){
            if(input != i+1){
                continue;
            }
            unverifiedList.get(i).setVerified(true);
            break;
        }

        System.out.println("\n\t1. Verify another parking spot");
        System.out.println("\t2. Back to admin menu");

        int input2 = SharedUtilities.getIntInput();

        switch(input2){
            case 1:
                verifyParkingSpots();

            case 2:
                return;

            default:
                System.out.println("\tNot a valid option");
                verifyParkingSpots();
        }
    } // end verifyParkingSpots();

    public void deleteParkingSpots() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("                - Delete Parking Spot -                ");
        System.out.println  ("-------------------------------------------------------");

        ArrayList<ParkingSpot> parkingSpotList = adminController.getParkingSpotList();
        SharedUtilities.printNumberedList(parkingSpotList);

        boolean validSelection = false;

        while(!validSelection) {

            int input = SharedUtilities.getIntInput();
            if (input == parkingSpotList.size() + 1) {
                return;
            } else if (SharedUtilities.selectionValidation(parkingSpotList, input)) {
                validSelection = true;
                int parkingSpotId = adminController.getParkingSpotId(parkingSpotList, input);
                adminController.deleteParkingSpot(parkingSpotId);
            }
        }

        System.out.println("\n\t1. Delete another parking spot");
        System.out.println("\t2. Back to admin menu");

        int input2 = SharedUtilities.getIntInput();
        switch(input2){
            case 1:
                deleteParkingSpots();

            case 2:
                return;

            default:
                System.out.println("\tNot a valid option");
                deleteParkingSpots();
        }

    } // end deleteParkingSpots();
}

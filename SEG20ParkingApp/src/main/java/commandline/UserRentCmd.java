package commandline;

import payment.Visa_Mastercard;
import utility.SharedUtilities;
import controller.UserRentController;
import model.ParkingSpot;
import model.User;

import java.util.ArrayList;
import java.util.Date;

public class UserRentCmd {

    private final UserRentController userRentController;
    private final User user;

    /* ------------------------------------------------------------------------------------------ */
    public UserRentCmd(UserRentController userRentController, User user) {
        this.userRentController = userRentController;
        this.user = user;

    }
    /* ------------------------------------------------------------------------------------------ */

    public void searchForParkingSpotMenu() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("               - Parking Spot Search -                 ");
        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("\t(Type quit to return.)");


        // get parking spots based on location search
        ArrayList<ParkingSpot> locationSearchResultList = locationSearch();
            if(locationSearchResultList == null) {
                return;
            }

        // date search     -    could not manage to extract method
        ArrayList<ParkingSpot> availableParkingSpotsList = null;
        boolean invalidDate = true;
        String dateValidationMessage;
        String dateInput;
        Date date = null;

        while(invalidDate) {
            System.out.print("\n\tEnter date (yyyy-mm-dd): ");

            // get string input
            dateInput = SharedUtilities.getStringInput();
            if(userRentController.checkIfQuit(dateInput))
                return;

            // if date is valid, get list of available parking spots
            if(userRentController.validDate(dateInput)) {
                invalidDate = false;

                date = userRentController.convertStringToDate(dateInput);
                if(date == null) {
                    return;
                }

                availableParkingSpotsList = userRentController.checkParkingSpotAvailability(locationSearchResultList, date);

                dateValidationMessage = userRentController.validateDateSearchResults(availableParkingSpotsList);
                System.out.println(dateValidationMessage);
            }
            else {
                System.out.println("\tNot a valid date.");
            }
        }

        selectParkingSpot(availableParkingSpotsList, date);
    }


    private ArrayList<ParkingSpot> locationSearch() {

        ArrayList<ParkingSpot> parkingSpotSearchList = null;
        String locationValidationMessage;

        while(SharedUtilities.arrayIsEmpty(parkingSpotSearchList)) {
            System.out.print("\n\tEnter city name: ");

            // get string input
            String locationInput = SharedUtilities.getStringInput();
            if(userRentController.checkIfQuit(locationInput))
                return null;

            // get results from search
            parkingSpotSearchList = userRentController.getLocationSearchResults(locationInput);

            // validate search results
            locationValidationMessage = userRentController.validateLocationSearchResults(parkingSpotSearchList);
            System.out.println(locationValidationMessage);
        }

        return parkingSpotSearchList;
    }


    private void selectParkingSpot(ArrayList<ParkingSpot> availableParkingSpotList, Date date) {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("               - Select Parking Spot -                 ");
        System.out.println  ("-------------------------------------------------------");

        System.out.println("\tSelect parking spot:");
        SharedUtilities.printNumberedList(availableParkingSpotList);

        boolean validSelection = false;

        while(!validSelection) {

            int input = SharedUtilities.getIntInput();

            // exit
            if (input == availableParkingSpotList.size() + 1) {
                return;
            }
            
            // verify input
            if (SharedUtilities.selectionValidation(availableParkingSpotList, input)) {
                validSelection = true;

                ParkingSpot selectedParkingSpot = userRentController.getSelectedParkingSpot(availableParkingSpotList, input);

                rentParkingSpot(selectedParkingSpot, date);
            }

            else {
                System.out.println("\tInvalid selection.");
            }
        }

    }



    private void rentParkingSpot(ParkingSpot parkingSpot, Date date) {

        if(selectPaymentMethod() == 0) {
            return;
        }

        SharedUtilities.processingAnimation();
        userRentController.reserveParkingSpot(parkingSpot, date, user);

        System.out.println("\n\tNew reservation made:");
        System.out.println("\t"+ parkingSpot + " \t\t" + SharedUtilities.formatTime(date));
    }


    private int selectPaymentMethod() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("               - Select Payment Method -               ");
        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("\t1. VISA / Mastercard");
        System.out.println  ("\t2. Cancel");

        boolean validSelection = false;

        while(!validSelection) {

            int input = SharedUtilities.getIntInput();

            if (input == 2) {
                System.out.println("\n\tPayment cancelled.\n");
                return 0;
            }

            if(Visa_Mastercard.verifyPayment()) {
                System.out.println("\n\tPayment verified.");
                validSelection = true;
            }
            else {
                System.out.println("\n\tPayment failed.");
            }
        }

        return 1;
    }

} // end UserRentCmd class

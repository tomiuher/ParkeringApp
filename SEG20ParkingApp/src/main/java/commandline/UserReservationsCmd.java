package commandline;

import utility.SharedUtilities;
import controller.UserReservationsController;
import model.Reservation;
import model.User;

import java.util.ArrayList;

public class UserReservationsCmd {

    private final UserReservationsController userReservationsController;
    private final User user;


    public UserReservationsCmd(UserReservationsController userReservationsController, User user) {
        this.userReservationsController = userReservationsController;
        this.user = user;

    }



    public void listReservations() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("                 - Your Reservations -                 ");
        System.out.println  ("-------------------------------------------------------");

        ArrayList<Reservation> reservationList = user.getReservationList();

        userReservationsController.printReservationList(reservationList);

    }



    public void deleteReservation() {

        System.out.println  ("-------------------------------------------------------");
        System.out.println  ("                - Delete Reservation -                 ");
        System.out.println  ("-------------------------------------------------------");

        ArrayList<Reservation> reservationList = user.getReservationList();

        if(reservationList.size() == 0) {
            System.out.println("\tYou have no reservations.");
            return;
        }

        SharedUtilities.printNumberedList(reservationList);

        boolean validSelection = false;

        while(!validSelection) {
            int input = SharedUtilities.getIntInput();

            if (input == reservationList.size() + 1) {
                return;
            }

            if (SharedUtilities.selectionValidation(reservationList, input)) {
                validSelection = true;

                userReservationsController.deleteReservation(reservationList, input, user);
            }
            else {
                System.out.println("\tInvalid selection.");
            }
        }

        System.out.println("\n\tReservation deleted.\n");
    }


}

package controller;

import model.ParkingSpot;
import model.Reservation;
import model.User;
import utility.SharedUtilities;

import java.util.ArrayList;
import java.util.Date;

public class UserReservationsController {



    public UserReservationsController() {
    }


    public void printReservationList(ArrayList<Reservation> reservationList) {

        if (reservationList.size() == 0) {
            System.out.println("\n\tYou have no reservations.\n");
        }
        else {
            System.out.println("\n\tParking Spot \t\t\t\t\tDate");
            for (Reservation res : reservationList) {
                System.out.println("\t" + res);
            }
        }
    }


    public void deleteReservation(ArrayList<Reservation> reservationList, int input, User user) {

        // get selected reservation
        Reservation selectedReservation = reservationList.get(input - 1);

        // get parking spot and date
        ParkingSpot reservedParkingSpot = selectedReservation.getReservedParkingSpot();
        Date reservedDate = selectedReservation.getReservedDate();

        // set parking spot date as UNOCCUPIED and delete reservation
        reservedParkingSpot.setParkingSpotUnoccupied(reservedDate);
        user.deleteReservation(selectedReservation);
    }



}

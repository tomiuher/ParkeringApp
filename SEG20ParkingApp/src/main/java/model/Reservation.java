package model;

import utility.SharedUtilities;

import java.util.Date;

public class Reservation {

    private static int idCounter;
    private final int reservationID;

    private final ParkingSpot reservedParkingSpot;
    private final Date reservedDate;
    private final User reservationUser;


    public Reservation(ParkingSpot reservedParkingSpot, Date reservedDate, User user) {
        this.reservationID = 1 + idCounter++;
        this.reservedParkingSpot = reservedParkingSpot;
        this.reservedDate = reservedDate;
        this.reservationUser = user;
    }


    public ParkingSpot getReservedParkingSpot() {
        return reservedParkingSpot;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public User getReservationUser() {
        return reservationUser;
    }

    public int getReservationID() {
        return reservationID;
    }


    @Override
    public String toString() {
        return reservedParkingSpot + "\t\t\t" + SharedUtilities.formatTime(reservedDate);
    }

}

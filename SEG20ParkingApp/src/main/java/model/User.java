package model;

import java.util.ArrayList;

public class User {


    private int userID;
    private int userMobileNumber;
    private String userEmail;

    private final ArrayList<String> userVehicleList;
    private final ArrayList<Reservation> userReservationList;

    public User(int userMobileNumber, ArrayList<Reservation> userReservationList, ArrayList<String> userVehicleList) {

        this.userMobileNumber = userMobileNumber;
        this.userReservationList = userReservationList;
        this.userVehicleList = userVehicleList;
    }



    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserMobileNumber() {
        return userMobileNumber;
    }
    public void setUserMobileNumber(int userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public ArrayList<String> getUserVehicleList() {
        return userVehicleList;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private void addCarRegistrationNumber(String carRegNumber) {
        userVehicleList.add(carRegNumber);
    }

    public ArrayList<Reservation> getReservationList() {
        return userReservationList;
    }


    public void addReservation(Reservation reservation) {
        userReservationList.add(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        for(Reservation res : userReservationList) {

            if (res == reservation) {
                userReservationList.remove(reservation);
                break;
            }
        }
    }


    @Override
    public String toString() {
        String userEmail = getUserEmail() == null ? "N/A" : getUserEmail();

        return "#" + userID + "\t\t\t" + userMobileNumber + "\t\t\t" + userEmail;
    }




}

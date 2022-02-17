package model;

import java.util.Date;
import java.util.HashMap;

public class ParkingSpot {
    public static final boolean UNOCCUPIED = true;
    public static final boolean OCCUPIED = false;

    private int parkingID;
    private String streetAddress, city, areaCode;
    private boolean verified;
    HashMap<Date, Boolean> parkingCalendar;


    public ParkingSpot(String streetAddress, String city, String areaCode, boolean verified, HashMap<Date, Boolean> parkingCalendar) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.areaCode = areaCode;
        this.verified = verified;
        this.parkingCalendar = parkingCalendar;
    }


    public int getParkingID() {
        return parkingID;
    }
    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }


    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public HashMap<Date, Boolean> getParkingCalendar() {
        return parkingCalendar;
    }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setParkingSpotUnoccupied(Date date) {

        this.getParkingCalendar().put(date, UNOCCUPIED);
    }

    public void setParkingSpotOccupied(Date date) {

        this.getParkingCalendar().put(date, OCCUPIED);
    }



    @Override
    public String toString() {
        return streetAddress + ", " + areaCode + " " + city;
    }
}

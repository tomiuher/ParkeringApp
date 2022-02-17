package controller;

import model.ParkingSpot;
import model.Reservation;
import model.User;
import repository.ParkingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserRentController {

    private final ParkingRepository parkingRepository;

    /* ------------------------------------------------------------------------------------------ */
    public UserRentController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }
    /* ------------------------------------------------------------------------------------------ */

    // LOCATION
    public ArrayList<ParkingSpot> getLocationSearchResults(String searchString) {
        ArrayList<ParkingSpot> searchResultList;

        searchResultList = parkingRepository.getParkingSpotListFromCityNameSearch(searchString);

        return searchResultList;
    }

    public String validateLocationSearchResults(ArrayList<ParkingSpot> parkingSpotSearchList) {
        String validationMessage;

        if(parkingSpotSearchList == null || parkingSpotSearchList.size() == 0)
            validationMessage = "\tNo parking spots found at this location.";
        else
            validationMessage = "\t" + parkingSpotSearchList.size() + " parking spots found at this location";

        return validationMessage;
    }
    /* ------------------------------------------------------------------------------------------ */


    // DATE
    public boolean validDate(String inputString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = simpleDateFormat.parse(inputString);
        }
        catch(ParseException e) {
            return false;
        }

        return date != null && !date.before(Calendar.getInstance().getTime());
    }


    public Date convertStringToDate(String inputString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(inputString);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }


    public String validateDateSearchResults(ArrayList<ParkingSpot> availableParkingSpotsList) {
        String validationMessage;

        if(availableParkingSpotsList == null || availableParkingSpotsList.size() == 0)
            validationMessage = "\tNo available parking spots at this date.";
        else
            validationMessage = "\t" + availableParkingSpotsList.size() + " parking spots available at this date.";

        return validationMessage;
    }


    public ArrayList<ParkingSpot> checkParkingSpotAvailability(ArrayList<ParkingSpot> parkingSpotArraylist, Date date) {
        ArrayList<ParkingSpot> availableParkingSpotsList;

        availableParkingSpotsList = parkingRepository.getAvailableParkingSpotsByDate(parkingSpotArraylist, date);

        return availableParkingSpotsList;
    }

    /* ------------------------------------------------------------------------------------------ */

    public ParkingSpot getSelectedParkingSpot(ArrayList<ParkingSpot> availableParkingSpotList, int input) {

        int selectedParkingSpotId = availableParkingSpotList.get(input - 1).getParkingID();

        return parkingRepository.getParkingSpotByID(selectedParkingSpotId);
    }

    public void reserveParkingSpot(ParkingSpot parkingSpot, Date date, User user) {
        parkingSpot.setParkingSpotOccupied(date);

        user.addReservation(new Reservation(parkingSpot, date, user));
    }

    /* ------------------------------------------------------------------------------------------ */

    public boolean checkIfQuit(String input) {
        return input.equalsIgnoreCase("quit");
    }

    /* ------------------------------------------------------------------------------------------ */


} // end UserRentController class

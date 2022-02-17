package repository;

import model.ParkingSpot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ParkingRepository {

    private ArrayList<ParkingSpot> parkingSpotList = new ArrayList<>();
    private int lastParkingID;

    // ---------------------------------------------------------------------------------- //

    public ParkingRepository() {

        ParkingSpot parkingSpot1 = new ParkingSpot("BRA veien 6D", "Halden",  "1783", true, new HashMap<>());
        ParkingSpot parkingSpot2 = new ParkingSpot("BRA veien 6C", "Bergen", "1783", true, new HashMap<>());
        ParkingSpot parkingSpot3 = new ParkingSpot("Plutoveien 3", "Oslo", "1234", true, new HashMap<>());
        ParkingSpot parkingSpot4 = new ParkingSpot("Plutoveien 4", "Oslo", "1234", true, new HashMap<>());

        addParkingSpot(parkingSpot1);
        addParkingSpot(parkingSpot2);
        addParkingSpot(parkingSpot3);
        addParkingSpot(parkingSpot4);

    }

    //just for test purposes
    public ParkingRepository(ParkingSpot ps1, ParkingSpot ps2, ParkingSpot ps3, ParkingSpot ps4){
        addParkingSpot(ps1);
        addParkingSpot(ps2);
        addParkingSpot(ps3);
        addParkingSpot(ps4);
    }

    // ---------------------------------------------------------------------------------- //

    public ArrayList<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public void addParkingSpot(ParkingSpot ps){
        ps.setParkingID(getNewParkingID());
        parkingSpotList.add(ps);
    }

    public int getNewParkingID(){
        lastParkingID++;
        return lastParkingID;
    }


    public ArrayList<ParkingSpot> getParkingSpotListFromCityNameSearch(String cityName) {
        ArrayList<ParkingSpot> returnList = new ArrayList<>();


        for (ParkingSpot parkingSpot : parkingSpotList) {

            if(parkingSpot.getCity().equalsIgnoreCase(cityName) && parkingSpot.isVerified()) {
                returnList.add(parkingSpot);
            }
        }
        return returnList;
    }

    public ArrayList<ParkingSpot> getVerifiedParkingSpots(){
        ArrayList<ParkingSpot> tempList = new ArrayList<>();
        for(ParkingSpot ps : parkingSpotList){
            if(ps.isVerified()){
                tempList.add(ps);
            }
        }
        return tempList;
    }

    public ArrayList<ParkingSpot> getUnverifiedParkingSpots(){
        ArrayList<ParkingSpot> tempList = new ArrayList<>();
        for(ParkingSpot ps : parkingSpotList){
            if(!ps.isVerified()){
                tempList.add(ps);
            }
        }
        return tempList;
    }

    public void deleteParkingSpot(int parkId){
        parkingSpotList.removeIf(ps -> ps.getParkingID() == parkId);
    }


    public ArrayList<ParkingSpot> getAvailableParkingSpotsByDate(ArrayList<ParkingSpot> parkingSpotQueryList, Date date) {
        ArrayList<ParkingSpot> returnList = new ArrayList<>();


        for (ParkingSpot parkingSpot : parkingSpotQueryList) {

            // if parking spot calendar contains search date AND it is not occupied, add to returnList
            if(parkingSpot.getParkingCalendar().containsKey(date) && parkingSpot.getParkingCalendar().get(date) == ParkingSpot.UNOCCUPIED) {
                returnList.add(parkingSpot);
            }

            // if parking spot calendar does NOT contain search date, add date to calendar and set to not occupied.
            else if(!parkingSpot.getParkingCalendar().containsKey(date)) {

                parkingSpot.setParkingSpotUnoccupied(date);
                returnList.add(parkingSpot);
            }
        }
        return returnList;
    }


    public ParkingSpot getParkingSpotByID(int id) {

        for (ParkingSpot parkingSpot : parkingSpotList) {
            if (parkingSpot.getParkingID() == id) {
                return parkingSpot;
            }
        }

        return null;
    }



} // end ParkingRepository class

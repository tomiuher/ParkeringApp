package controller;

import model.*;
import repository.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRentOutController {

    ArrayList<ParkingSpot> rentedOutList = new ArrayList<>();
    ArrayList<ParkingSpot> myParkingSpots = new ArrayList<>();

    public UserRentOutController() {
        addParkingSpot(new ParkingSpot("Slottsplassen 1", "Oslo", "0010", true, new HashMap<>()));
    }

    public void addParkingSpot(ParkingSpot ps){
        myParkingSpots.add(ps);
    }

    public void rentOutParkingSpot(int input){
        input--; //was 1 higher than loop number

        for(int i = 0; i < myParkingSpots.size(); i++){
            if(input == i && !myParkingSpots.get(i).isVerified()){
                System.out.println("This parking spot has not been verified yet, this may take up to 48 hours");
                break;
            }
            else if(input == i){
                rentedOutList.add(myParkingSpots.get(i));
                //myParkingSpots.remove(input);
                break;
            }
        }
    }

    public void removeFromRentedOutList(int input){
        input--; //was 1 higher than loop number

        for(int i = 0; i < rentedOutList.size(); i++){
            if(input == i){
                rentedOutList.remove(rentedOutList.get(i));
                break;
            }
        }
    }

    public ArrayList<ParkingSpot> getRentedOutList() {
        return rentedOutList;
    }

    public ArrayList<ParkingSpot> getMyParkingSpots(){
        return myParkingSpots;
    }
}

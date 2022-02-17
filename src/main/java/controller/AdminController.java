package controller;

import model.*;
import repository.*;

import java.util.ArrayList;

public class AdminController {

    UserRepository userRepository;
    ParkingRepository parkingRepository;

    public AdminController(UserRepository userRepository, ParkingRepository parkingRepository) {
        this.userRepository = userRepository;
        this.parkingRepository = parkingRepository;
    }

    public ArrayList<User> getUserList(){
        return userRepository.getUserList();
    }

    public int getUserId(int input) {
        int currentUserId = 0;
        for (User user : getUserList()) {
            if (input == user.getUserID()) {
                currentUserId = user.getUserID();
                break;
            }
        }
        return currentUserId;
    }

    public User getUser(int userId){
        return userRepository.getUser(userId);
    }

    public void deleteUser(int userId){
        userRepository.deleteUser(userId);
    }

    //------------------ Parking spot functions ------------------

    public ArrayList<ParkingSpot> getVerifiedParkingSpots(){
        return parkingRepository.getVerifiedParkingSpots();
    }

    public ArrayList<ParkingSpot> getUnverifiedParkingSpots(){
        return parkingRepository.getUnverifiedParkingSpots();
    }

    public void deleteParkingSpot(int parkId){
        parkingRepository.deleteParkingSpot(parkId);
    }

    public ArrayList<ParkingSpot> getParkingSpotList(){
        return parkingRepository.getParkingSpotList();
    }

    public int getParkingSpotId(ArrayList<ParkingSpot> parkingSpotList, int input) {
        return parkingSpotList.get(input - 1).getParkingID();
    }


}

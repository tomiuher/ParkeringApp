package controllerTests;

import controller.AdminController;
import model.*;
import org.junit.jupiter.api.*;
import repository.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminControllerTests {

    private AdminController adminController;

    @BeforeEach
    public void setup(){

        ParkingSpot parkingSpot1 = new ParkingSpot("Vei 1", "Bergen", "9020", false, new HashMap<>());
        ParkingSpot parkingSpot2 = new ParkingSpot("BRA veien 6D", "Halden", "1783", false, new HashMap<>());
        ParkingSpot parkingSpot3 = new ParkingSpot("Gulveien 5", "Halden", "1234", false, new HashMap<>());
        ParkingSpot parkingSpot4 = new ParkingSpot("Fredriks gate", "Oslo", "4321", false, new HashMap<>());
        ParkingRepository parkingRepository = new ParkingRepository(parkingSpot1, parkingSpot2, parkingSpot3, parkingSpot4);

        User user1 = new User(12345678, new ArrayList<>(), new ArrayList<>());
        User user2 = new User(87654321, new ArrayList<>(), new ArrayList<>());
        User user3 = new User(47149251, new ArrayList<>(), new ArrayList<>());
        UserRepository userRepository = new UserRepository(user1, user2, user3);

        adminController = new AdminController(userRepository, parkingRepository);
    }

    @AfterEach
    public void afterMethod(){
        adminController = null;
    }


    @Test
    public void ifUserExists_getUserId_returnsIdOfUser(){
        assertEquals(2, adminController.getUserId(2));
    }

    @Test
    public void ifUserDoesNotExist_getUserId_returns_0(){
        assertEquals(0, adminController.getUserId(5));
    }

    @Test
    public void getParkingSpotId_returnsIdOfParkingSpotFromListAndInput() {
        ArrayList<ParkingSpot> parkingSpotList = adminController.getParkingSpotList();
        int input = 1;

        int expected = parkingSpotList.get(0).getParkingID();
        int actual = adminController.getParkingSpotId(parkingSpotList, input);

        assertEquals(expected, actual);
    }

    @Test
    public void ifParkingSpotExists_getParkingSpotId_returnsIdOfParkingSpot(){
        ArrayList<ParkingSpot> parkingSpotList = adminController.getParkingSpotList();
        assertEquals(3, adminController.getParkingSpotId(parkingSpotList, 3));
    }

}

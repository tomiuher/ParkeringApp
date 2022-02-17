package controllerTests;

import controller.UserRentOutController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentOutControllerTests {

    private UserRentOutController controller;

    @BeforeEach
    public void setup(){
        controller = new UserRentOutController();
    }

    @AfterEach
    public void afterMethod(){
        controller = null;
    }


    // tester krav Utleie.parkering.registrering
    @Test
    public void rentOutParkingSpot_adds_ParkingSpot_to_rentedOutList(){
        controller.rentOutParkingSpot(1); //adds 1st element from myParkingSpots to rentedOutList
        assertEquals(controller.getMyParkingSpots().get(0), controller.getRentedOutList().get(0));
    }

    @Test
    public void removeFromRentedOutList(){
        controller.rentOutParkingSpot(1);

        assertEquals(1, controller.getRentedOutList().size());
        controller.removeFromRentedOutList(1);
        assertEquals(0, controller.getRentedOutList().size());
    }
}

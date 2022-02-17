import controller.UserRentController;
import model.ParkingSpot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ParkingRepository;
import utility.SharedUtilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SharedUtilitiesTests {

    private ParkingRepository parkingRepository;
    private UserRentController urc;

    /* ------------------------------------------------------------------------------------------ */
    @BeforeEach
    void setup() {
        ParkingSpot parkingSpot1 = new ParkingSpot("BRA Veien 1", "Halden", "1111", true, new HashMap<>());
        ParkingSpot parkingSpot2 = new ParkingSpot("BRA Veien 2", "Halden", "1111", true, new HashMap<>());
        ParkingSpot parkingSpot3 = new ParkingSpot("Osloveien 1", "Oslo", "2222", true, new HashMap<>());
        ParkingSpot parkingSpot4 = new ParkingSpot("Drammensveien 1", "Drammen", "3333", true, new HashMap<>());
        parkingRepository = new ParkingRepository(parkingSpot1, parkingSpot2, parkingSpot3, parkingSpot4);

        urc = new UserRentController(parkingRepository);
    }

    @AfterEach
    void reset() {
        parkingRepository = null;
        urc = null;
    }
    /* ------------------------------------------------------------------------------------------ */

    @Test
    void ifArrayIsEmpty_arrayIsEmpty_returnsTrue() {
        assertTrue(SharedUtilities.arrayIsEmpty(new ArrayList<>()));
    }

    @Test
    void ifArrayIsNotEmpty_arrayIsEmpty_returnsFalse() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();

        assertFalse(SharedUtilities.arrayIsEmpty(parkingSpotList));
    }

    @Test
    void selectionValidation_returnsTrueIfInputIsValid() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        int input = 1;

        assertTrue(SharedUtilities.selectionValidation(parkingSpotList, input));
    }

    @Test
    void selectionValidation_returnsFalseIfInputIsInvalid() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        int input = -1;

        assertFalse(SharedUtilities.selectionValidation(parkingSpotList, input));
    }


    @Test
    void printNumberedList_printsArrayList() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();

        String test = "\t1. BRA Veien 1, 1111 Halden\n" +
                "\t2. BRA Veien 2, 1111 Halden\n" +
                "\t3. Osloveien 1, 2222 Oslo\n" +
                "\t4. Drammensveien 1, 3333 Drammen\n" +
                "\t5. Exit";

        String expected = test.replaceAll("\n", "").replaceAll("\r", "");

        // Generously provided by stack overflow
            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);

        SharedUtilities.printNumberedList(parkingSpotList);

        String actual = baos.toString().replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(expected, actual);

            // Put things back
            System.out.flush();
            System.setOut(old);
    }


    @Test
    void getStringInput_returnsInputFromScanner() {
        String expected = "Hello";

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Hello".getBytes());
        System.setIn(in);

        String actual = SharedUtilities.getStringInput();

        assertEquals(expected, actual);

        System.setIn(sysInBackup);
    }




}

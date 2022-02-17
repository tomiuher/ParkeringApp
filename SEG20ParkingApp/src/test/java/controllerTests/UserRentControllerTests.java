package controllerTests;

import controller.UserRentController;
import model.ParkingSpot;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ParkingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class UserRentControllerTests {

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

    // Tester krav Leie.søk.by
    @Test
    void getLocationSearchResults_returnsListFromSearch() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();

        ArrayList<ParkingSpot> expected = new ArrayList<>();
        expected.add(parkingSpotList.get(0));
        expected.add(parkingSpotList.get(1));

        ArrayList<ParkingSpot> actual = urc.getLocationSearchResults("Halden");

        assertEquals(expected, actual);
    }

    @Test
    void ifParkingSpotListIsNotEmpty_validateSearchLocationResults_returnsNumberOfParkingSpotsFound() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        String expected = "\t4 parking spots found at this location";

        String actual = urc.validateLocationSearchResults(parkingSpotList);

        assertEquals(expected, actual);
    }

    @Test
    void ifParkingSpotListIsEmpty_validateSearchLocationResults_returnsErrorMessage() {
        ArrayList<ParkingSpot> parkingSpotList = new ArrayList<>();
        String expected = "\tNo parking spots found at this location.";

        String actual = urc.validateLocationSearchResults(parkingSpotList);

        assertEquals(expected, actual);
    }

    @Test
    void ifDateIsValid_validDate_returnTrue() {
        String dateInputString = "2020-12-12";

        assertTrue(urc.validDate(dateInputString));
    }

    @Test
    void ifDateIsNotValid_validDate_returnsFalse() {
        String dateInputString = "not a valid date";

        assertFalse(urc.validDate(dateInputString));
    }

    @Test
    void ifStringIsDate_convertStringToDate_returnsDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateInputString = "2020-12-12";

        Date expected = simpleDateFormat.parse("2020-12-12");
        Date actual = urc.convertStringToDate(dateInputString);

        assertEquals(expected, actual);
    }

    @Test
    void ifStringIsNotDate_convertStringToDate_returnsNull() {
        Date date = urc.convertStringToDate("Not a date");

        assertNull(date);
    }

    @Test
    void ifAvailableParkingSpotListIsNotEmpty_validateSearchResults_returnsNumberOfAvailableParkingSpots() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        String expected = "\t4 parking spots available at this date.";

        String actual = urc.validateDateSearchResults(parkingSpotList);

        assertEquals(expected, actual);
    }

    @Test
    void ifAvailableParkingSpotListIsEmpty_validateSearchResults_returnsErrorMessage() {
        ArrayList<ParkingSpot> parkingSpotList = new ArrayList<>();
        String expected = "\tNo available parking spots at this date.";

        String actual = urc.validateDateSearchResults(parkingSpotList);

        assertEquals(expected, actual);
    }

    // tester krav Leie.søk.dato
    @Test
    void checkParkingSpotAvailability_returnsListOfAvailableParkingSpots() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        String dateInputString = "2020-12-12";
        Date date = urc.convertStringToDate(dateInputString);

        ArrayList<ParkingSpot> expected = parkingRepository.getParkingSpotList();

        ArrayList<ParkingSpot> actual = urc.checkParkingSpotAvailability(parkingSpotList, date);

        assertEquals(expected, actual);
    }

    @Test
    void getSelectedParkingSpot_returnsParkingSpotFromListBasedOnInput() {
        ArrayList<ParkingSpot> parkingSpotList = parkingRepository.getParkingSpotList();
        ParkingSpot expected = parkingSpotList.get(0);
        int input = 1;

        ParkingSpot actual = urc.getSelectedParkingSpot(parkingSpotList, input);

        assertEquals(expected, actual);
    }

    @Test
    void reserveParkingSpot_setsParkingSpotAsOccupied() {
        ParkingSpot parkingSpot = parkingRepository.getParkingSpotByID(1);
        String dateInputString = "2020-12-12";
        Date date = urc.convertStringToDate(dateInputString);
        User user = new User(1, new ArrayList<>(), new ArrayList<>());

        urc.reserveParkingSpot(parkingSpot, date, user);

        assertFalse(parkingSpot.getParkingCalendar().get(date));
    }

    // tester krav Leie.reservere / Leie.reservere.dato
    @Test
    void reserveParkingSpot_addsNewReservationToUser() {
        ParkingSpot parkingSpot = parkingRepository.getParkingSpotByID(1);
        String dateInputString = "2020-11-11";
        Date date = urc.convertStringToDate(dateInputString);
        User user = new User(1, new ArrayList<>(), new ArrayList<>());

        assertEquals(0, user.getReservationList().size());

        urc.reserveParkingSpot(parkingSpot, date, user);

        assertNotNull(user.getReservationList().get(0));
        assertEquals(1, user.getReservationList().size());
    }



    @Test
    void ifInputIsQuit_checkIfQuit_returnsTrue() {
        String input = "quit";

        assertTrue(urc.checkIfQuit(input));
    }

    @Test
    void ifInputIsNotQuit_checkIfQuit_returnsFalse() {
        String input = "this is not quit";

        assertFalse(urc.checkIfQuit(input));
    }

}

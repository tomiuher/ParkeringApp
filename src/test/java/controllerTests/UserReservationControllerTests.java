package controllerTests;

import controller.UserReservationsController;
import model.ParkingSpot;
import model.Reservation;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserReservationControllerTests {

    private User testUser;
    private final UserReservationsController urc = new UserReservationsController();

    /* ------------------------------------------------------------------------------------------ */
    @BeforeEach
    void setup() throws ParseException {
        HashMap<Date, Boolean> testCalendar = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date testDate1 = simpleDateFormat.parse("2020-01-01");
            Date testDate2 = simpleDateFormat.parse("2020-02-02");
            testCalendar.put(testDate1, false);
            testCalendar.put(testDate2, false);

        ParkingSpot testParkingSpot = new ParkingSpot("Test Road 1", "TestCity", "1111", true, testCalendar);
        testUser = new User(11111111, new ArrayList<>(), new ArrayList<>());

        Reservation res1 = new Reservation(testParkingSpot, testDate1, testUser);
        Reservation res2 = new Reservation(testParkingSpot, testDate2, testUser);

        testUser.addReservation(res1);
        testUser.addReservation(res2);
    }

    @AfterEach
    void afterMethod() {
        testUser = null;
    }
    /* ------------------------------------------------------------------------------------------ */

    // Tester krav Leie.reservere.liste
    @Test
    void ifArrayListIsNotEmpty_printReservationList_printsArrayList() {
        ArrayList<Reservation> reservationList = testUser.getReservationList();

        String reservationListString =  "\n" + "\n" +
                                        "\tParking Spot \t\t\t\t\tDate\n" +
                                        "\tTest Road 1, 1111 TestCity\t\t\tons., jan. 1, 2020\n" +
                                        "\tTest Road 1, 1111 TestCity\t\t\tsøn., feb. 2, 2020";

        String expected = reservationListString.replaceAll("\n", "").replaceAll("\r", "");

        String expected2 = "\tParking Spot \t\t\t\t\tDate\tTest Road 1, 1111 TestCity\t\t\tWed, Jan 1, 2020\tTest Road 1, 1111 TestCity\t\t\tSun, Feb 2, 2020";

            // Generously provided by stack overflow
            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);

        urc.printReservationList(reservationList);

        String actual = baos.toString().replaceAll("\n", "").replaceAll("\r", "");

        assertThat(actual, anyOf(is(expected), is(expected2)));

            // Put things back
            System.out.flush();
            System.setOut(old);
    }

    @Test
    void ifArrayListIsEmpty_printReservationList_printsErrorMessage() {
        ArrayList<Reservation> reservationList = new ArrayList<>();

        String reservationListString =  "\n" + "\n" +
                                        "\tYou have no reservations.";

        String expected = reservationListString.replaceAll("\n", "").replaceAll("\r", "");

            // Generously provided by stack overflow
            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);

        urc.printReservationList(reservationList);

        String actual = baos.toString().replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(expected, actual);

            // Put things back
            System.out.flush();
            System.setOut(old);
    }

    // Tester krav Leie.reservere.slette
    @Test
    void deleteReservation_removesReservationFromReservationList() {
        ArrayList<Reservation> reservationList = testUser.getReservationList();
        int input = 1; // index 0

        urc.deleteReservation(reservationList, input, testUser);

        assertEquals(1, reservationList.size());
    }

    @Test
    void deleteReservation_setsParkingSpotToUnoccupied() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate1 = simpleDateFormat.parse("2020-01-01");

        ArrayList<Reservation> reservationList = testUser.getReservationList();
        int input = 1; // index 0

        urc.deleteReservation(reservationList, input, testUser);

        ParkingSpot parkingSpot = testUser.getReservationList().get(0).getReservedParkingSpot();

        assertTrue(parkingSpot.getParkingCalendar().get(testDate1));
    }

}

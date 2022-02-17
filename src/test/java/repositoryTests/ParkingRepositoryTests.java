package repositoryTests;

import model.ParkingSpot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import repository.ParkingRepository;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingRepositoryTests {

    ParkingRepository repo;

    @BeforeEach
    public void setup(){
        ParkingSpot parkingSpot1 = new ParkingSpot("Vei 1", "Bergen", "9172", false, new HashMap<>());
        ParkingSpot parkingSpot2 = new ParkingSpot("BRA veien 6A", "Halden", "1783", false, new HashMap<>());
        ParkingSpot parkingSpot3 = new ParkingSpot("Neptunveien 15", "Oslo", "1234", true, new HashMap<>());
        ParkingSpot parkingSpot4 = new ParkingSpot("Fredriks gate", "Oslo", "1279", true, new HashMap<>());
        repo = new ParkingRepository(parkingSpot1, parkingSpot2, parkingSpot3, parkingSpot4);
    }

    @AfterEach
    public void afterMethod(){
        repo = null;
    }

    @Test
    public void ifStringContainsValidCityName_getParkingSpotListFromCityNameSearch_returnsListOfAvailableParkingSpotsBySearchString(){
        int expected = 2;
        int actual = repo.getParkingSpotListFromCityNameSearch("oslo").size();

        assertEquals(expected, actual);
    }

    @Test
    void ifStringDoesNotContainValidCityName_getParkingSpotListFromCityNameSearch_returnsEmptyList() {
        int expected = 0;
        int actual = repo.getParkingSpotListFromCityNameSearch("asdasdads").size();

        assertEquals(expected, actual);
    }


    @Test
    public void ifParkingSpotExists_getParkingSpotByID_returnsParkingSpot(){
        assertEquals(repo.getParkingSpotList().get(0), repo.getParkingSpotByID(1));
    }

    @Test
    public void ifParkingSpotDoesNotExist_getParkingSpotByID_returnsNull(){
        assertNull(repo.getParkingSpotByID(6));
    }


    // tester krav Utleie.admin.parkeringsplass.verifisering
    @Test
    public void testVerifyParkingSpot(){
        repo.getParkingSpotByID(1).setVerified(true);
        assertTrue(repo.getParkingSpotByID(1).isVerified());
    }

    @Test
    public void testGetVerifiedParkingSpots(){
        assertEquals(2, repo.getVerifiedParkingSpots().size());
        repo.getParkingSpotByID(1).setVerified(true);
        repo.getParkingSpotByID(2).setVerified(true);
        assertEquals(4, repo.getVerifiedParkingSpots().size());
    }

    @Test
    public void testGetUnverifiedParkingSpots(){
        assertEquals(2, repo.getUnverifiedParkingSpots().size());
        repo.getParkingSpotByID(1).setVerified(true);
        assertEquals(1, repo.getUnverifiedParkingSpots().size());
    }



    // Tester krav Utleie.admin.parkeringsplass.slette
    @Test
    public void testDeleteParkingSpot(){

        //if parkingId 2 exists, set expected to 2
        int expected = 0;
        for(ParkingSpot ps : repo.getParkingSpotList()){
            if(ps.getParkingID() == 2){
                expected = 2;
                break;
            }
        }

        repo.deleteParkingSpot(2);

        //if parking Id 2 still exists, set actual to 0 (test would fail if ParkingSpot was not deleted)
        int actual = 2;
        for(ParkingSpot ps : repo.getParkingSpotList()){
            if(ps.getParkingID() == 2){
                actual = 0;
                break;
            }
        }
        assertEquals(expected, actual);
    }
}

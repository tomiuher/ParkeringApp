package repositoryTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import repository.*;
import model.*;

import java.util.ArrayList;

public class UserRepositoryTests {

    //Setter opp et test repository
    UserRepository repo = new UserRepository(
            new User(12348671, new ArrayList<>(), new ArrayList<>()),
            new User(39649039, new ArrayList<>(), new ArrayList<>()),
            new User(41056105, new ArrayList<>(), new ArrayList<>())
    );

    //Sjekker egentlig bare om en hvilken som helst user er slettet, ikke akkurat den vi ber den om
    @Test
    public void testDeleteUser(){
        repo.deleteUser(1);
        assertEquals(2, repo.getUserList().size());
    }
}

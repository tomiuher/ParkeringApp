import commandline.MainMenuCmd;
import repository.ParkingRepository;
import repository.UserRepository;

public class Main {


    public static void main(String[] args) {

        UserRepository ur = new UserRepository();
        ParkingRepository pr = new ParkingRepository();

        MainMenuCmd cmd = new MainMenuCmd(pr, ur);

        cmd.mainMenu();
    }


}

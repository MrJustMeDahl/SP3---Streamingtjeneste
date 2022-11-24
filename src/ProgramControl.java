import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramControl {

    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<AMedia> allMedia = new ArrayList<>();
    public static User currentUser;
    public static MainMenu mainMenu;
    public static DataHandling dataHandling;
    public static boolean usingDatabase = false;

    public ProgramControl(){
    }

    public void runProgram(){
        dataHandling = new DatabaseHandling();
        DatabaseHandling database = (DatabaseHandling) dataHandling;
        if(!database.isDatabaseOnline){
            dataHandling = new FileHandling();
        } else {
            usingDatabase = true;
        }
        ArrayList<AMedia> movieList = dataHandling.readMovieData("Data/MovieData.txt");
        ArrayList<AMedia> seriesList = dataHandling.readSeriesData("Data/SeriesData.txt");
        for(AMedia m: movieList){
            allMedia.add(m);
        }
        for(AMedia m: seriesList){
            allMedia.add(m);
        }
        allUsers = dataHandling.readUserData("Data/UserData.txt", allMedia);

        StartMenu startMenu = new StartMenu(allUsers);
        currentUser = startMenu.runStartMenu();
        if(!allUsers.contains(currentUser)){
            allUsers.add(currentUser);
            ProgramControl.dataHandling.writeUserData("Data/UserData.txt", allUsers);
        }

        mainMenu = new MainMenu(allUsers, movieList, seriesList);
        mainMenu.runMainMenu();

        dataHandling.writeUserData("Data/UserData.txt", allUsers);
    }
}
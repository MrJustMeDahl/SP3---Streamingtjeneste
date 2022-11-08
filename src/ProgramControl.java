import java.util.ArrayList;

public class ProgramControl {

    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<AMedia> allMedia = new ArrayList<>();
    public static User currentUser;

    public ProgramControl(){
    }

    public void runProgram(){
        ArrayList<AMedia> movieList = FileHandling.readFromMovieFile("Data/MovieData.txt");
        ArrayList<AMedia> seriesList = FileHandling.readFromSeriesFile("Data/SeriesData.txt");
        for(AMedia m: movieList){
            allMedia.add(m);
        }
        for(AMedia m: seriesList){
            allMedia.add(m);
        }
        allUsers = FileHandling.readFromUserFile("Data/UserData.txt", allMedia);

        StartMenu startMenu = new StartMenu(allUsers);
        currentUser = startMenu.runStartMenu();
        if(!allUsers.contains(currentUser)){
            allUsers.add(currentUser);
        }

        MainMenu mainMenu = new MainMenu(allMedia);
        mainMenu.runMainMenu();

        FileHandling.writeToUserFile("Data/UserData.txt", allUsers);
    }
}
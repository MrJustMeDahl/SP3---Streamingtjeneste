import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramControl {

    private ArrayList<User> allUsers = new ArrayList<>(User);
    private ArrayList<AMedia> allMedia = new ArrayList<>(AMedia);
    public static User currentUser;
    public static Scanner userInput = new Scanner(System.in);

    public ProgramControl(){
    }

    public void runProgram(){
        ArrayList<AMedia> movieList = readFromMovieFile("Data/MovieData.txt");
        ArrayList<AMedia> seriesList = readFromSeriesFile("Data/SeriesData.txt");
        for(AMedia m: movieList){
            allMedia.add(m);
        }
        for(AMedia m: seriesList){
            allMedia.add(m);
        }
        allUsers = readFromUserFile("Data/UserData.txt");

        StartMenu startMenu = new StartMenu(allUsers);
        currentUser = startMenu.runStartMenu();

        MainMenu mainMenu = new MainMenu(allMedia);
        mainMenu.runMainMenu();

        writeToFile();

    }


    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public ArrayList<AMedia> readFromMovieFile(String path){
        ArrayList<AMedia> mediaFromFiles = new ArrayList<AMedia>();
        File file = new File(path);
        try {
            Scanner scanMovies = new Scanner(file);
            scanMovies.nextLine();
            do {
                String input = scanMovies.nextLine();
                String[] separatedInput = input.split("; ");
                String mediaName = separatedInput[0];
                int mediaReleaseYear = Integer.parseInt(separatedInput[1]);
                String mediaCategory = separatedInput[2];
                String ratingReadyForParse = separatedInput[3].replace(',','.');
                float mediaRating = Float.parseFloat(ratingReadyForParse);
                AMedia media = new Movie(mediaName, mediaReleaseYear, mediaCategory, mediaRating);
                mediaFromFiles.add(media);
            } while (scanMovies.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("Failed to load movie data.");
        }
        return mediaFromFiles;
    }

    public ArrayList<AMedia> readFromSeriesFile(String path){
        ArrayList<AMedia> mediaFromFiles = new ArrayList<AMedia>();
        File file = new File(path);
        try {
            Scanner scanMedia = new Scanner(file);
            scanMedia.nextLine();
            do {
                String input = scanMedia.nextLine();
                String[] separatedInput = input.split("; ");
                String mediaName = separatedInput[0];
                String[] separateYear = separatedInput[1].split("-");
                int mediaReleaseYearFrom = Integer.parseInt(separateYear[0]);
                int mediaReleaseYearTill = 0;
                if(!separateYear[1].equals(" ")){
                    mediaReleaseYearTill = Integer.parseInt(separateYear[1]);
                }
                String mediaCategory = separatedInput[2];
                String ratingReadyForParse = separatedInput[3].replace(',','.');
                float mediaRating = Float.parseFloat(ratingReadyForParse);
                String[] separateSeasons = separatedInput[4].split(", ");
                for(int i = 0; i < separateSeasons.length; i++){
                    String[] separateEpisodes = separateSeasons[i].split("-");
                    int season = Integer.parseInt(separateEpisodes[0]);
                    int numberOfEpisodes = Integer.parseInt(separateEpisodes[1]);
                    for(int episode = 1; episode <= numberOfEpisodes; episode++){
                        AMedia media = new Series(mediaName, mediaReleaseYearFrom, mediaReleaseYearTill, mediaCategory, mediaRating, season, episode);
                        mediaFromFiles.add(media);
                    }
                }
            } while (scanMedia.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("Failed to load series data.");
        }
        return mediaFromFiles;
    }

    private ArrayList<User> readFromUserFile(String path){
        ArrayList<User> usersFromFile = new ArrayList<User>();
        File userFile = new File(path);
        try {
            Scanner scanUsers = new Scanner(userFile);
            scanUsers.nextLine();
            do{
                String input = scanUsers.nextLine();
                String[] separatedInput = input.split("; ");
                String username = separatedInput[0];
                String password = separatedInput[1];
                int age = Integer.parseInt(separatedInput[2]);
                String[] separatedWatchedMedia = separatedInput[3].split(", ");
                ArrayList<AMedia> watchedMedia = new ArrayList<AMedia>();
                for(int i = 0; i < separatedWatchedMedia.length; i++){
                    for(AMedia m: allMedia){
                        if(m.getName.equals(separatedWatchedMedia[i])){
                            watchedMedia.add(m);
                        }
                    }
                }
                String[] separatedSavedMedia = separatedInput[4].split(", ");
                ArrayList<AMedia> savedMedia = new ArrayList<AMedia>();
                for(int i = 0; i < separatedSavedMedia.length; i++){
                    for(AMedia m: allMedia){
                        if(m.getName.equals(separatedSavedMedia[i])){
                            savedMedia.add(m);
                        }
                    }
                }
                User user = new User(username, password, age, watchedMedia, savedMedia);
                usersFromFile.add(user);
            } while(scanUsers.hasNextLine());
        } catch (FileNotFoundException e){
            System.out.println("Failed to load user data.");
        }
        return usersFromFile;
    }

    public void writeToFile(){

    }
}

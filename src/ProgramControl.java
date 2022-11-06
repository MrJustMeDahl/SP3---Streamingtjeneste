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
        ArrayList<AMedia> movieList = readFromMovieFile("Data/MovieData");
        ArrayList<AMedia> seriesList = readFromSeriesFile("Data/SeriesData");
        for(AMedia m: movieList){
            allMedia.add(m);
        }
        for(AMedia m: seriesList){
            allMedia.add(m);
        }

        StartMenu startMenu = new StartMenu(allUsers);
        currentUser = startMenu.runStartMenu();


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
                float mediaRating = Float.parseFloat(separatedInput[3]);
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
                float mediaRating = Float.parseFloat(separatedInput[3]);
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

    public void writeToFile(){

    }
}

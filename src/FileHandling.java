import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {
    public static ArrayList<AMedia> readFromMovieFile(String path){
        ArrayList<AMedia> mediaFromFiles = new ArrayList<>();
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
                String ratingReadyForParse = separatedInput[3].replace(',','.').replace(";", "");
                float mediaRating = Float.parseFloat(ratingReadyForParse);
                AMedia media = new Movie(mediaName, mediaCategory, mediaRating, mediaReleaseYear);
                mediaFromFiles.add(media);
            } while (scanMovies.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("Failed to load movie data.");
        }
        return mediaFromFiles;
    }

    public static ArrayList<AMedia> readFromSeriesFile(String path){
        ArrayList<AMedia> mediaFromFiles = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scanMedia = new Scanner(file);
            scanMedia.nextLine();
            do {
                String input = scanMedia.nextLine();
                String[] separatedInput = input.split("; ");
                String mediaName = separatedInput[0];
                String[] separateYear = separatedInput[1].split("-");
                int mediaReleaseYearFrom = Integer.parseInt(separateYear[0].trim());
                int mediaReleaseYearTill = 0;
                if(!separateYear[1].equals(" ")){
                    mediaReleaseYearTill = Integer.parseInt(separateYear[1].trim());
                }
                String mediaCategory = separatedInput[2];
                String ratingReadyForParse = separatedInput[3].replace(',','.');
                float mediaRating = Float.parseFloat(ratingReadyForParse);
                String[] separateSeasons = separatedInput[4].split(", ");
                for(int i = 0; i < separateSeasons.length; i++){
                    String[] separateEpisodes = separateSeasons[i].split("-");
                    int season = Integer.parseInt(separateEpisodes[0].trim());
                    int numberOfEpisodes = Integer.parseInt(separateEpisodes[1].replace(";", "").trim());
                    for(int episode = 1; episode <= numberOfEpisodes; episode++){
                        AMedia media = new Series(mediaName, mediaCategory, mediaRating, mediaReleaseYearFrom, season, episode, mediaReleaseYearTill);
                        mediaFromFiles.add(media);
                    }
                }
            } while (scanMedia.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("Failed to load series data.");
        }
        return mediaFromFiles;
    }

    public static ArrayList<User> readFromUserFile(String path, ArrayList<AMedia> allMedia){
        ArrayList<User> usersFromFile = new ArrayList<>();
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
                ArrayList<AMedia> watchedMedia = new ArrayList<>();
                for(int i = 0; i < separatedWatchedMedia.length; i++){
                    for(AMedia m: allMedia){
                        if(m.getName().equals(separatedWatchedMedia[i])){
                            watchedMedia.add(m);
                        }
                    }
                }
                String[] separatedSavedMedia = separatedInput[4].replace(";", "").split(", ");
                ArrayList<AMedia> savedMedia = new ArrayList<>();
                for(int i = 0; i < separatedSavedMedia.length; i++){
                    for(AMedia s: allMedia){
                        if(s.getName().equals(separatedSavedMedia[i])){
                            savedMedia.add(s);
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

    public static void writeToUserFile(String path, ArrayList<User> allUsers){
        File userFile = new File(path);
        try{
            FileWriter writer = new FileWriter(userFile);
            writer.write("username; password; age; watchedmedia; savedmedia;\n");
            for(User u: allUsers){
                String watchedMedia = "";
                String savedMedia = "";
                for(AMedia m: u.getWatchedMedia()){
                    watchedMedia += m.getName() + ", ";
                }
                if(watchedMedia.length() > 0) {
                    watchedMedia = watchedMedia.substring(0, (watchedMedia.length() - 2));
                }
                for(AMedia s: u.getSavedMedia()){
                    savedMedia += s.getName() + ", ";
                }
                if(savedMedia.length() > 0) {
                    savedMedia = savedMedia.substring(0, (savedMedia.length() - 2));
                }
                writer.write(u.getUsername() + "; " + u.getPassword() + "; " + u.getAge() + "; " + watchedMedia + "; " + savedMedia + ";\n");
            }
            writer.close();
        }catch (IOException ex){
            System.out.println("Failed to save user data.");
        }
    }

    public static ArrayList<String> readFromCategoryFile(String path){
        ArrayList<String> listOfCategories = new ArrayList<>();
        File file = new File(path);
        try{
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            do{
                String category = scanner.nextLine();
                listOfCategories.add(category);
            } while(scanner.hasNextLine());
        }catch(FileNotFoundException e){
            System.out.println("Failed to load category data.");
        }
        return listOfCategories;
    }

}

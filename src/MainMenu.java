import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<AMedia> allMedia = new ArrayList<AMedia>();
    Scanner scanner = new Scanner(System.in);

    public MainMenu(ArrayList<AMedia> allMedia) {
        this.allMedia = allMedia;
    }
/*
    public void runMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + ProgramControl.currentUser.getUsername() + ", please enter an option below.");
        while (true) {
            System.out.println("1 - Get suggestions.");
            System.out.println("2 - Search.");
            System.out.println("3 - log out.");
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                suggestedMedia();
            }
            if (userInput.equals("2")) {
                searchEngine();
            }
            if (userInput.equals("3")) {
                logOut();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
    }

    private void suggestedMedia() {
        AMedia lastPlayedMedia = ProgramControl.currentUser.getWatchedMedia.get(size - 1);
        String lastPlayedCategory = lastPlayedMedia.getCategory;
        String[] lastPlayedCategories = lastPlayedCategory.split(", ");
        Random rnd = new Random();
        int randomCategory = rnd.nextInt(0, lastPlayedCategories.length);
        String chosenCategoryOne = lastPlayedCategories[randomCategory];
        randomCategory = rnd.nextInt(0, lastPlayedCategories.length);
        String chosenCategoryTwo = lastPlayedCategories[randomCategory];
        randomCategory = rnd.nextInt(0, lastPlayedCategories.length);
        String chosenCategoryThree = lastPlayedCategories[randomCategory];
        ArrayList<AMedia> listOfMediaFromCategory = new ArrayList<>();
        for (AMedia m : allMedia) {
            if (m.getCategory.contains(chosenCategoryOne)) {
                listOfMediaFromCategory.add(m);
            }
        }
        int randomMediaFromList = rnd.nextInt(0, listOfMediaFromCategory.size());
        AMedia suggestion1 = listOfMediaFromCategory.get(randomMediaFromList);
        System.out.println("We have found these options you might like: ");
        System.out.println("1 - " + suggestion1);


    }
*/
    private void searchEngine() {
        System.out.println("Search options: ");
        System.out.println("Press '1' for Movie: ");
        System.out.println("Press '2' for Series: ");
        System.out.println("Press '3' to return to Main");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                searchMovies();
            }
            if (userInput.equals("2")) {
                searchSeries();
            }
            if (userInput.equals("3")) {
                runMainMenu();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }

    }

    private AMedia searchMovies() {
        System.out.println("Movies: ");
        System.out.println("Press '1' for Name: ");
        System.out.println("Press '2' for Category: ");
        System.out.println("Press '3' for Rating: ");
        System.out.println("Press '4' for Year of release: ");
        System.out.println("Press '5' to return to Search: ");
        while (true) {
            String userInput = scanner.nextLine();

                if (userInput.equals("1")) {
                    searchMovieName();
                }
                if (userInput.equals("2")) {
                    System.out.println("Available Categories: \n " + AMedia.getCategory().split(","));
                    System.out.println("Enter the Category you wish to search: ");
                    String movieCategory = scanner.nextLine();
                    if (movieCategory == allMedia) {
                        return movieCategory;
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                if (userInput.equals("3")) {
                    System.out.println("Enter the minimum rating you wish to see: ");
                    String movieRating = scanner.nextLine();
                    if (movieRating <= allMedia) {
                        return movieRating;
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                if (userInput.equals("4")) {
                    System.out.println("Enter the release Year: ");
                    String movieReleaseYear = scanner.nextLine();
                    if (movieReleaseYear <= allMedia) {
                        return movieReleaseYear;
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                if (userInput.equals("5")) {
                    searchEngine();
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }

    }

    private void searchSeries() {
        System.out.println("Series: ");
        System.out.println("Press '1' for Name: ");
        System.out.println("Press '2' for Category: ");
        System.out.println("Press '3' for Rating: ");
        System.out.println("Press '4' for Year of release: ");
        System.out.println("Press '5' for Year of final season: ");
        System.out.println("Press '6' to return to Search: ");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
               searchSerieName();
            }
            if (userInput.equals("2")) {
                System.out.println("Available Categories: \n " + AMedia.getCategory().split(","));
                System.out.println("Enter the Category you wish to search: ");
                String serieCategory = scanner.nextLine();
                if (serieCategory == AMedia.getCategory()) {
                    System.out.println("write the media you wish to watch: \n "+ Series.getCategory(serieCategory));
                    String chosenMedia = scanner.nextLine();
                    if (chosenMedia = Series.getName()){
                        searchSerieName();
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }
            if (userInput.equals("3")) {
                System.out.println("Enter the minimum rating you wish to see: ");
                String serieRating = scanner.nextLine();
                if (serieRating <= AMedia.getRating().split(",")) {
                    System.out.println("write the media you wish to watch: \n "+ Series.getRating(serieRating));
                    String chosenMedia = scanner.nextLine();
                    if (chosenMedia = Series.getRating()){
                        searchSerieName();
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }
            if (userInput.equals("4")) {
                System.out.println("Enter the release Year: ");
                String serieReleaseYear = scanner.nextLine();
                if (serieReleaseYear <= AMedia.getReleaseYear()) {
                    System.out.println("write the media you wish to watch: \n "+ Series.getReleaseYear(serieReleaseYear));
                    String chosenMedia = scanner.nextLine();
                    if (chosenMedia = Series.getReleaseYear()){
                        searchSerieName();
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }
            if (userInput.equals("5")) {
                System.out.println("Enter the Year of the final season: ");
                String serieFinalSeason = scanner.nextLine();
                if (serieFinalSeason <= Series.getEndYear()) {
                    System.out.println("write the media you wish to watch: \n "+ Series.getEndYear(serieFinalSeason));
                    String chosenMedia = scanner.nextLine();
                    if (chosenMedia = Series.getEndYear()){
                        searchSerieName();
                    }
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }
            if (userInput.equals("6")) {
                searchEngine();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }


    }

    private void searchMovieName(){
        System.out.println(" Movies:\n " + AMedia.getName());
        System.out.println("Please enter the name of the movie you wish to see: ");
        String chosenMovie = scanner.nextLIne();
        if (chosenMovie == AMedia.Name()){
            Movie.playMedia();
        }
        System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
    }

    private void searchSerieName() {
        System.out.println("Seasons:\n " + allMedia);
        System.out.println("Please enter from which season you wish to watch: ");
        String searchSerieSeason = scanner.nextLine();
        if (searchSerieSeason == Series.getSeason()) {
            System.out.println("Which Episode do you wish to watch: ");
            System.out.println("Episodes:\n " + Series.getEpisode());
            String searchSerieSeasonEpisode = scanner.nextLine();
            if (searchSerieSeasonEpisode == Series.getEpisode()) {
                Series.playMedia();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
        System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
    }


}

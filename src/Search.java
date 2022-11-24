import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Search {

    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AMedia> allMovies;
    private final ArrayList<AMedia> allSeries;
    private final ArrayList<String> allCategories;


    public Search  (ArrayList<AMedia>allMovies, ArrayList<AMedia>allSeries, ArrayList<String>allCategories) {
    this.allMovies = allMovies;
    this.allSeries=allSeries;
    this.allCategories = allCategories;
}

//*********
//Search Engine Start
//*********
    public void searchEngine() {
        System.out.println("Search options: ");
        System.out.println("1 - by Movie: ");
        System.out.println("2 - by Series: ");
        System.out.println("3 - to return to Main");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                searchMovies(allMovies);
            }
            if (userInput.equals("2")) {
                searchSeries(allSeries);
            }
            if (userInput.equals("3")) {
                ProgramControl.mainMenu.runMainMenu();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }

    }

    //*********
//Overview over Movie search options
//*********
    private AMedia searchMovies(ArrayList<AMedia> allMovies) {
        if(ProgramControl.currentUser.getAge() < 18){
            ArrayList<AMedia> listOfFamilyMovies = new ArrayList<>();
            for(AMedia m: allMovies){
                if(m.getCategory().contains("Family")){
                    listOfFamilyMovies.add(m);
                }
            }
            allMovies = listOfFamilyMovies;
        }
        System.out.println("Movies: ");
        System.out.println("1 - by Name: ");
        System.out.println("2 - by Category: ");
        System.out.println("3 - by Rating: ");
        System.out.println("4 - by Year of release: ");
        System.out.println("5 - to return to Search: ");
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("1")) {
                searchByMediaName(allMovies);
            }
            if (userInput.equals("2")) {
                searchByCategory(allMovies, allCategories);
            }
            if (userInput.equals("3")) {
                searchByRating(allMovies);
            }
            if (userInput.equals("4")) {
                searchByYear(allMovies);
            }
            if (userInput.equals("5")) {
                searchEngine();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
    }

    //*********
//Overview over Series search options
//*********
    private AMedia searchSeries(ArrayList<AMedia> allSeries) {
        if(ProgramControl.currentUser.getAge() < 18){
            ArrayList<AMedia> listOfFamilySeries = new ArrayList<>();
            for(AMedia m: allSeries){
                if(m.getCategory().contains("Family")){
                    listOfFamilySeries.add(m);
                }
            }
            allSeries = listOfFamilySeries;
        }
        System.out.println("Series: ");
        System.out.println("1 - by Name: ");
        System.out.println("2 - by Category: ");
        System.out.println("3 - by Rating: ");
        System.out.println("4 - by Year of release: ");
        System.out.println("5 - to return to Search: ");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                searchByMediaName(allSeries);
            }
            if (userInput.equals("2")) {
                searchByCategory(allSeries, allCategories);

            }
            if (userInput.equals("3")) {
                searchByRating(allSeries);
            }
            if (userInput.equals("4")) {
                searchByYear(allSeries);
            }

            if (userInput.equals("5")) {
                searchEngine();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
    }


    //*********
//Search by Name
//*********
    private void searchByMediaName(ArrayList<AMedia> listOfMedia) {

        System.out.println("Please enter a title you wish to see: ");
        String userInput = scanner.nextLine();
        ArrayList<AMedia> mediaByName = new ArrayList<>();
        for (AMedia m : listOfMedia) {
            if (m.getName().toLowerCase().contains(userInput.toLowerCase())) {
                mediaByName.add(m);
            }
        }
        if(mediaByName.size() == 0){
            System.out.println("There is no media that matches your search. Please try again.");
            searchByMediaName(listOfMedia);
        }
        System.out.println("You can choose the following: \n");
        int i;
        for (i = 0; i < mediaByName.size(); i++) {
            System.out.println(i + 1 + " - " + mediaByName.get(i).getName());
        }
        System.out.println("\n" + (i+1) + " - Return to main menu.");
        String input2 = scanner.nextLine();
        int userInput2 = -1;
        try {
            userInput2 = Integer.parseInt(input2);
            if(userInput2 == i+1){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (userInput2 > mediaByName.size() || userInput2 <= 0) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchByMediaName(listOfMedia);
            }
        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchByMediaName(listOfMedia);
        }
        mediaByName.get(userInput2 - 1).chooseMedia();
    }

    //*********
//Choose a Category to search from
//*********
    private void searchByCategory(ArrayList<AMedia> listOfMedia, ArrayList<String> allCategories) {
        ArrayList<AMedia> listOfMediaAltered = new ArrayList<>();
        ArrayList<String> listOfCategoriesAltered = new ArrayList<>();
        if(ProgramControl.currentUser.getAge() < 18){
            listOfCategoriesAltered.add("Family");
            for(AMedia m: listOfMedia){
                if(m.getCategory().contains("Family")){
                    listOfMediaAltered.add(m);
                    String[] mCategories = m.getCategory().split(", ");
                    for(int i = 0; i < mCategories.length; i++){
                        if(!listOfCategoriesAltered.contains(mCategories[i])){
                            listOfCategoriesAltered.add(mCategories[i]);
                        }
                    }
                }
            }
        }
        if(ProgramControl.currentUser.getAge() >= 18){
            listOfMediaAltered = listOfMedia;
            listOfCategoriesAltered = allCategories;
        }
        System.out.println("Choose your desired Category: ");
        int i;
        for (i = 0; i < listOfCategoriesAltered.size(); i++) {
            System.out.println(i + 1 + " - " + listOfCategoriesAltered.get(i));
        }
        System.out.println("\n" + (i+1) + " - Return to main menu.");
        String chooseCategory = scanner.nextLine();
        int chosenCategory = -1;
        try {
            chosenCategory = Integer.parseInt(chooseCategory);
            if(chosenCategory == i+1){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (chosenCategory >= 0 || chosenCategory < listOfCategoriesAltered.size()) {
                searchByChosenCategory(listOfCategoriesAltered.get(chosenCategory - 1), listOfMediaAltered);
            }
        } catch (Exception e) {
            System.out.println("The option you have chosen is not valid. Please try again.");
            searchByCategory(listOfMedia, allCategories);
        }
    }

    //*********
//Search by ChosenCategory
//*********
    private void searchByChosenCategory(String chosenCategory, ArrayList<AMedia> listOfMedia) {
        ArrayList<AMedia> MediaFromCategory = new ArrayList<>();

        for (AMedia m : listOfMedia) {
            if (m.getCategory().contains(chosenCategory)) {
                MediaFromCategory.add(m);
            }

        }
        System.out.println("Please choose a desired movie from the list");
        for (int i = 0; i < MediaFromCategory.size(); i++) {
            System.out.println(i + 1 + " - " + MediaFromCategory.get(i).getName());
        }
        String selectedInput = scanner.nextLine();
        int userInput = -1;
        try {
            userInput = Integer.parseInt(selectedInput);
            if (userInput > MediaFromCategory.size() || userInput <= 0) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchByChosenCategory(chosenCategory, listOfMedia);

            }

        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchByChosenCategory(chosenCategory, listOfMedia);
        }
        MediaFromCategory.get(userInput - 1).chooseMedia();
    }

    //*********
//Search by Rating
//*********
    private void searchByRating(ArrayList<AMedia> media) {
        System.out.println("Please enter the minimum rating of movies you wish to see, ");
        System.out.println("or press B to return to the main menu.");
        String userInput = scanner.nextLine();
        double minimumRating = -1.0;
        try {
            if(userInput.equalsIgnoreCase("b")){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (userInput.length() == 1) {
                userInput += ".0";
            }
            minimumRating = Double.parseDouble(userInput.replace(",", "."));
            if (minimumRating <= 0 || minimumRating > 10) {
                System.out.println("The typed Rating is either too low or too high, please try again: ");
                searchByRating(media);
            }
        } catch (Exception e) {
            System.out.println("The typed Rating doesn't exist, please try again: ");
            searchByRating(media);
        }
        ArrayList<AMedia> mediaByRating = new ArrayList<>();
        for (AMedia m : media) {
            if(m.getRating() >= minimumRating && ProgramControl.currentUser.getAge() < 18){
                if(m.getCategory().contains("Family")){
                    mediaByRating.add(m);
                }
            }
            if (m.getRating() >= minimumRating && ProgramControl.currentUser.getAge() >= 18) {
                mediaByRating.add(m);
            }
        }
        System.out.println("Choose what you would like to watch: ");
        int i;
        for (i = 0; i < mediaByRating.size(); i++) {
            System.out.println(i + 1 + " - " + mediaByRating.get(i).getName());
        }
        System.out.println("\n" + (i+1) + " - Return to main menu.");
        String selectedInput = scanner.nextLine();
        int userInput2 = -1;
        try {
            userInput2 = Integer.parseInt(selectedInput);
            if(userInput2 == i+1){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (userInput2 > mediaByRating.size() || userInput2 <= 0) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchByRating(media);
            }
        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchByRating(media);
        }
        mediaByRating.get(userInput2 - 1).chooseMedia();
    }

    //*********
//Search by Year of release
//*********
    private void searchByYear(ArrayList<AMedia> media) {
        System.out.println("Please enter the desired year of release,");
        System.out.println("or press B to return to the main menu.");
        String userInput = scanner.nextLine();
        int yearOfRelease = -1;
        try {
            if(userInput.equalsIgnoreCase("b")){
                ProgramControl.mainMenu.runMainMenu();
            }
            yearOfRelease = Integer.parseInt(userInput);
            if (yearOfRelease == -1) {
                System.out.println(userInput + " is not a valid year.");
                searchByYear(media);
            }
        } catch (Exception e) {
            System.out.println("The typed year doesn't exist, please try again: ");
            searchByYear(media);
        }
        ArrayList<AMedia> mediaByYearOfRelease = new ArrayList<>();
        for (AMedia y : media) {
            if (y.getReleaseYear() == yearOfRelease || y.getReleaseYear() == yearOfRelease - 1 || y.getReleaseYear() == yearOfRelease + 1) {
                mediaByYearOfRelease.add(y);
            }
        }
        if(ProgramControl.currentUser.getAge() < 18) {
            for (AMedia m : mediaByYearOfRelease) {
                if (!m.getCategory().contains("Family")) {
                    mediaByYearOfRelease.remove(m);
                }
            }
        }
        if(mediaByYearOfRelease.size() > 0) {
            System.out.println("Choose what you would like to watch: ");
            for (int i = 0; i < mediaByYearOfRelease.size(); i++) {
                System.out.println(i + 1 + " - " + mediaByYearOfRelease.get(i).getName());
            }
        } else {
            System.out.println("There is no media available for the year you have chosen. Please try again.");
            searchByYear(media);
        }
        String selectedInput = scanner.nextLine();
        int userInput2 = -1;
        try {
            userInput2 = Integer.parseInt(selectedInput);
            if (userInput2 > mediaByYearOfRelease.size() || userInput2 <= 0) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchByYear(media);
            }
        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchByYear(media);
        }
        mediaByYearOfRelease.get(userInput2 - 1).chooseMedia();
    }

    public void searchBySavedMedia() {
        ArrayList<AMedia> savedMedia = ProgramControl.currentUser.getSavedMedia();
        if (savedMedia.size() == 0) {
            System.out.println("You don't have anything saved.");
            System.out.println("Returning to main menu.");
            try {
                sleep(2500);
            } catch (Exception e) {

            }
            for (int i = 0; i < 25; i++) {
                System.out.println("\n");
            }
            ProgramControl.mainMenu.runMainMenu();
        }
        System.out.println("What would you like to watch from your saved list?");
        int i;
        for (i = 0; i < savedMedia.size(); i++) {
            System.out.println(i + 1 + " - " + savedMedia.get(i).getName());
        }
        System.out.println("\n" +(i+1) + " - Return to main menu.");
        String userInput = scanner.nextLine();
        int chosenMedia = -1;
        try {
            chosenMedia = Integer.parseInt(userInput);
            if(chosenMedia == i+1){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (chosenMedia < 0 || chosenMedia > savedMedia.size()) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchBySavedMedia();
            }
        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchBySavedMedia();
        }
        savedMedia.get(chosenMedia - 1).chooseMedia();
    }

    public void searchByWatchedMedia() {
        ArrayList<AMedia> watchedMedia = ProgramControl.currentUser.getWatchedMedia();
        if (watchedMedia.size() == 0) {
            System.out.println("You haven't watched anything yet.");
            System.out.println("Returning to main menu.");
            try {
                sleep(2500);
            } catch (Exception e) {

            }
            for (int i = 0; i < 25; i++) {
                System.out.println("\n");
            }
            ProgramControl.mainMenu.runMainMenu();
        }
        System.out.println("What would you like to watch again?");
        int i;
        for (i = 0; i < watchedMedia.size(); i++) {
            System.out.println(i + 1 + " - " + watchedMedia.get(i).getName());
        }
        System.out.println("\n" + (i+1) + " - Return to main menu.");
        String userInput = scanner.nextLine();
        int chosenMedia = -1;
        try {
            chosenMedia = Integer.parseInt(userInput);
            if(chosenMedia == i+1){
                ProgramControl.mainMenu.runMainMenu();
            }
            if (chosenMedia < 0 || chosenMedia > watchedMedia.size()) {
                System.out.println("The chosen option doesn't exist, please try again.");
                searchByWatchedMedia();
            }
        } catch (Exception e) {
            System.out.println("The chosen option doesn't exist, please try again. ");
            searchByWatchedMedia();
        }
        watchedMedia.get(chosenMedia - 1).chooseMedia();
    }
}


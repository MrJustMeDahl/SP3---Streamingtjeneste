import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MainMenu {

    private ArrayList<User> allUsers;
    private ArrayList<AMedia> allMedia;
    private ArrayList<AMedia> allMovies;
    private ArrayList<AMedia> allSeries;
    private ArrayList<String> allcategories = new ArrayList<>();
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);
    public MainMenu(ArrayList<AMedia> allMedia, ArrayList<User> allUsers, ArrayList<AMedia> allMovies, ArrayList<AMedia> allSeries){
        this.allMedia = allMedia;
        this.allUsers = allUsers;
        this.allMovies = allMovies;
        this.allSeries = allSeries;

        allcategories.add("Action");
        allcategories.add("Adventure");
        allcategories.add("Biography");
        allcategories.add("Comedy");
        allcategories.add("Crime");
        allcategories.add("Drama");
        allcategories.add("Family");
        allcategories.add("Fantasy");
        allcategories.add("Film-Noir");
        allcategories.add("History");
        allcategories.add("Horror");
        allcategories.add("Musical");
        allcategories.add("Mystery");
        allcategories.add("Romance");
        allcategories.add("Sci-fi");
        allcategories.add("Sport");
        allcategories.add("Thriller");
        allcategories.add("War");
        allcategories.add("Western");
    }

    public void runMainMenu(){

        System.out.println("Welcome " + ProgramControl.currentUser.getUsername() + ", please enter an option below.");
        while(true) {
            System.out.println("1 - Get suggestions.");
            System.out.println("2 - Search.");
            System.out.println("3 - Log out.");
            System.out.println("4 - User options.");
            String userInput = scanner.nextLine();
            switch(userInput){
                case "1":
                    suggestedMedia();
                    break;
                case "2":
                    searchEngine();
                    break;
                case "3":
                    logOut();
                    break;
                case "4":
                    userOptions();
                    break;
                default:
                    System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }
        }
    }

    private void suggestedMedia() {
        ArrayList<AMedia> allMoviesAltered = allMovies;
        AMedia suggestion1 = allMoviesAltered.get(rnd.nextInt(0, allMoviesAltered.size()));
        AMedia suggestion2 = allMoviesAltered.get(rnd.nextInt(0, allMoviesAltered.size()));
        AMedia suggestion3 = allMoviesAltered.get(rnd.nextInt(0, allMoviesAltered.size()));
            if(ProgramControl.currentUser.getWatchedMedia().size() > 0) {
                //Finding the last played media from the current user.
                AMedia lastPlayedMedia = ProgramControl.currentUser.getWatchedMedia().get(ProgramControl.currentUser.getWatchedMedia().size() - 1);
                //Finding the category/categories of the last played media and picking a random category between them.
                String lastPlayedCategory = lastPlayedMedia.getCategory();
                String[] lastPlayedCategories = lastPlayedCategory.split(", ");


                String chosenCategory = getRandomCategory(lastPlayedCategories);
                //Creating a list of media that has the same category as the randomly chosen categories, and picking a random media from the list. Do this for all 3 randomly chosen categories.
                suggestion1 = getRandomMediaFromCategory(chosenCategory, allMoviesAltered);
                chosenCategory = getRandomCategory(lastPlayedCategories);
                allMoviesAltered.remove(suggestion1);
                suggestion2 = getRandomMediaFromCategory(chosenCategory, allMoviesAltered);
                chosenCategory = getRandomCategory(lastPlayedCategories);
                allMoviesAltered.remove(suggestion2);
                suggestion3 = getRandomMediaFromCategory(chosenCategory, allMoviesAltered);
            }

            System.out.println("We have found these options you might like: ");
            System.out.println("1 - " + suggestion1.getName() + ".");
            System.out.println("2 - " + suggestion2.getName() + ".");
            System.out.println("3 - " + suggestion3.getName() + ".");
            System.out.println("4 - Go back to main menu.");
            String userInput = scanner.nextLine().trim();
            switch (userInput) {
                case "1":
                    suggestion1.chooseMedia();
                    break;
                case "2":
                    suggestion2.chooseMedia();
                    break;
                case "3":
                    suggestion3.chooseMedia();
                    break;
                case "4":
                    runMainMenu();
                    break;
                default:
                    System.out.println("The option you have entered does not exist.\nPlease try again.");
                    suggestedMedia();
            }
        }
    private AMedia getRandomMediaFromCategory(String category, ArrayList<AMedia> allMoviesAltered){
        ArrayList<AMedia> listOfMediaFromCategory = new ArrayList<>();
        for(AMedia m: allMoviesAltered) {
            if(m.getCategory().contains(category)){
                listOfMediaFromCategory.add(m);
            }
        }
        if(listOfMediaFromCategory.size() > 0) {
            int randomMediaFromList = rnd.nextInt(0, listOfMediaFromCategory.size());
            return listOfMediaFromCategory.get(randomMediaFromList);
        }
        return allMoviesAltered.get(rnd.nextInt(0, allMoviesAltered.size()));
    }

    private String getRandomCategory(String[] lastPlayedCategories){
        int randomCategory = rnd.nextInt(0,lastPlayedCategories.length);
        return lastPlayedCategories[randomCategory];
    }

    public void logOut() {
        FileHandling.writeToUserFile("Data/UserData.txt", allUsers);
        System.out.println("We look forward to see you again!");
        try {
            sleep(5000);
        } catch (InterruptedException e) {

        }
        for(int i = 0; i < 25; i++){
            System.out.println("\n");
        }
        ProgramControl pc = new ProgramControl();
        pc.runProgram();
    }


//*********
//Search Engine Start
//*********
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

//*********
//Overview over Movie search options
//*********
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
                searchByMediaName(allMovies);
            }
            if (userInput.equals("2")) {
                searchByCategory(allMovies);
            }
            if (userInput.equals("3")) {
                searchByRating(allMovies);
            }
            if (userInput.equals("4")) {
                searchByYearOfRelease(allMovies);
            }
            if (userInput.equals("5")) {
                searchEngine();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
    }

    public void userOptions() {
        System.out.println("You have these options to alter your user settings:");
        System.out.println("1 - Change username.");
        System.out.println("2 - Change password.");
        System.out.println("3 - Delete account.");
        System.out.println("4 - Return to main menu.");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                System.out.println("Please enter your new username or press B go back: ");
                String username = scanner.nextLine();
                if (username.equalsIgnoreCase("b")) {
                    userOptions();
                }
                ProgramControl.currentUser.setUsername(username);
                break;
            case "2":
                System.out.println("Please enter your new username or press B go back: ");
                String password = scanner.nextLine();
                if (password.equalsIgnoreCase("b")) {
                    userOptions();
                }
                ProgramControl.currentUser.setPassword(password);
                break;
            case "3":
                System.out.println("Are you sure that you want to delete your account?");
                System.out.println("Press y to delete your account - this can not be undone.");
                System.out.println("Press any other key to go back.");
                String userChoice = scanner.nextLine();
                if (userChoice.equalsIgnoreCase("y")) {
                    System.out.println("Thank you for trying EverythingMedia.");
                    allUsers.remove(ProgramControl.currentUser);
                    logOut();
                } else {
                    userOptions();
                }
                break;
            case "4":
                runMainMenu();
                break;
            default:
                System.out.println("The option you have chosen does not exist. Please try again.");
                userOptions();
        }
    }

//*********
//Overview over Series search options
//*********
            private AMedia searchSeries () {
                System.out.println("Series: ");
                System.out.println("Press '1' for Name: ");
                System.out.println("Press '2' for Category: ");
                System.out.println("Press '3' for Rating: ");
                System.out.println("Press '4' for Year of release: ");
                System.out.println("Press '5' to return to Search: ");
                while (true) {
                    String userInput = scanner.nextLine();
                    if (userInput.equals("1")) {
                        searchByMediaName(allSeries);
                    }
                    if (userInput.equals("2")) {
                        searchByCategory(allSeries);

                    }
                    if (userInput.equals("3")) {
                        searchByRating(allSeries);
                    }
                    if (userInput.equals("4")) {
                        searchByYearOfRelease(allSeries);
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
            private void searchByMediaName (ArrayList < AMedia > listOfMedia) {

                System.out.println("Please enter the Title you wish to see: ");
                String userInput = scanner.nextLine();
                ArrayList<AMedia> mediaByName = new ArrayList<>();
                for (AMedia m : listOfMedia) {
                    if (m.getName().toLowerCase().contains(userInput.toLowerCase())) {
                        mediaByName.add(m);
                    }

                }
                System.out.println("You can choose the following: \n");

                for( int i =0; i<mediaByName.size(); i++ ){
                    System.out.println(i+1+" - " + mediaByName.get(i).getName());
                }
                String input2 = scanner.nextLine();
                int userInput2 = -1;
                try {
                    userInput2 = Integer.parseInt(input2);
                    if (userInput2 > mediaByName.size() || userInput2 <= 0) {
                        System.out.println("The chosen movie doesn't exist, please try again.");
                        searchByMediaName(listOfMedia);

                    }

                } catch (Exception e) {
                    System.out.println("The chosen movie doesn't exist, please try again. ");
                    searchByMediaName(listOfMedia);
                }
                mediaByName.get(userInput2 -1).chooseMedia();
            }

//*********
//Choose a Category to search from
//*********
            private void searchByCategory (ArrayList < AMedia > listOfMedia) {
                System.out.println("Choose a Category you wish to watch: ");
                for (int i = 0; i < allcategories.size(); i++) {
                    System.out.println(i + 1 + " - " + allcategories.get(i));
                }
                String chooseCategory = scanner.nextLine();
                switch (chooseCategory) {
                    case "1":
                        searchByChosenCategory(allcategories.get(0), listOfMedia);
                        break;

                    case "2":
                        searchByChosenCategory(allcategories.get(1), listOfMedia);
                        break;

                    case "3":
                        searchByChosenCategory(allcategories.get(2), listOfMedia);
                        break;

                    case "4":
                        searchByChosenCategory(allcategories.get(3), listOfMedia);
                        break;

                    case "5":
                        searchByChosenCategory(allcategories.get(4), listOfMedia);
                        break;

                    case "6":
                        searchByChosenCategory(allcategories.get(5), listOfMedia);
                        break;

                    case "7":
                        searchByChosenCategory(allcategories.get(6), listOfMedia);
                        break;

                    case "8":
                        searchByChosenCategory(allcategories.get(7), listOfMedia);
                        break;

                    case "9":
                        searchByChosenCategory(allcategories.get(8), listOfMedia);
                        break;

                    case "10":
                        searchByChosenCategory(allcategories.get(9), listOfMedia);
                        break;

                    case "11":
                        searchByChosenCategory(allcategories.get(10), listOfMedia);
                        break;

                    case "12":
                        searchByChosenCategory(allcategories.get(11), listOfMedia);
                        break;

                    case "13":
                        searchByChosenCategory(allcategories.get(12), listOfMedia);
                        break;

                    case "14":
                        searchByChosenCategory(allcategories.get(13), listOfMedia);
                        break;

                    case "15":
                        searchByChosenCategory(allcategories.get(14), listOfMedia);
                        break;

                    case "16":
                        searchByChosenCategory(allcategories.get(15), listOfMedia);
                        break;

                    case "17":
                        searchByChosenCategory(allcategories.get(16), listOfMedia);
                        break;

                    case "18":
                        searchByChosenCategory(allcategories.get(17), listOfMedia);
                        break;

                    case "19":
                        searchByChosenCategory(allcategories.get(18), listOfMedia);
                        break;

                    default:
                        System.out.println("Can't register your answer please try again: ");
                        searchByCategory(listOfMedia);
                }

            }

//*********
//Search by ChosenCategory
//*********
            private void searchByChosenCategory (String chosenCategory, ArrayList < AMedia > listOfMedia){
                ArrayList<AMedia> MediaFromCategory = new ArrayList<>();

                for (AMedia m : listOfMedia) {
                    if (m.getCategory().contains(chosenCategory)) {
                        MediaFromCategory.add(m);
                    }

                }
                System.out.println("Please choose a movie from the list");
                for (int i = 0; i < MediaFromCategory.size(); i++) {
                    System.out.println(i + 1 + " - " + MediaFromCategory.get(i).getName());
                }
                String selectedInput = scanner.nextLine();
                int userInput = -1;
                try {
                    userInput = Integer.parseInt(selectedInput);
                    if (userInput > MediaFromCategory.size() || userInput <= 0) {
                        System.out.println("The chosen movie doesn't exist, please try again.");
                        searchByChosenCategory(chosenCategory, listOfMedia);

                    }

                } catch (Exception e) {
                    System.out.println("The chosen movie doesn't exist, please try again. ");
                    searchByChosenCategory(chosenCategory, listOfMedia);
                }
                MediaFromCategory.get(userInput - 1).chooseMedia();
            }

//*********
//Search by Rating
//*********
            private void searchByRating (ArrayList < AMedia > Media) {
                System.out.println("Please enter the minimum rating of movies you wish to see.");
                String userInput = scanner.nextLine();
                double minimumRating = -1.0;
                try {
                    if (userInput.length() == 1) {
                        userInput += ".0";
                    }

                    minimumRating = Double.parseDouble(userInput.replace(",", "."));
                    if (minimumRating <= 0 || minimumRating > 10) {
                        System.out.println("The typed Rating doesn't exist, please try again: ");
                        searchByRating(Media);
                    }

                } catch (Exception e) {
                    System.out.println("The typed Rating doesn't exist, please try again: ");
                    searchByRating(Media);
                }
                ArrayList<AMedia> mediaByRating = new ArrayList<>();
                for (AMedia m : Media) {

                    if (m.getRating() >= minimumRating) {
                        mediaByRating.add(m);

                    }

                }
                System.out.println("Choose the movie you wish to see: ");
                for (int i = 0; i < mediaByRating.size(); i++) {
                    System.out.println(i + 1 + " - " + mediaByRating.get(i).getName());
                }
                String selectedInput = scanner.nextLine();
                int userInput2 = -1;
                try {
                    userInput2 = Integer.parseInt(selectedInput);
                    if (userInput2 > mediaByRating.size() || userInput2 <= 0) {
                        System.out.println("The chosen movie doesn't exist, please try again.");
                        searchByRating(Media);

                    }

                } catch (Exception e) {
                    System.out.println("The chosen movie doesn't exist, please try again. ");
                    searchByRating(Media);
                }
                allMedia.get(userInput2).chooseMedia();

            }

//*********
//Search by Year of release
//*********
            private void searchByYearOfRelease (ArrayList < AMedia > Media) {
                System.out.println("Please enter the wanted year.");
                String userInput = scanner.nextLine();
                int yearOfRelease = -1;
                try {

                    yearOfRelease = Integer.parseInt(userInput);
                    if (yearOfRelease == -1) {
                        System.out.println("The typed Year doesn't exist, please try again: ");
                        searchByYearOfRelease(Media);
                    }

                } catch (Exception e) {
                    System.out.println("The typed Year doesn't exist, please try again: ");
                    searchByYearOfRelease(Media);
                }
                ArrayList<AMedia> mediaByYearOfRelease = new ArrayList<>();
                for (AMedia y : Media) {

                    if (y.getReleaseYear() >= yearOfRelease) {
                        mediaByYearOfRelease.add(y);

                    }

                }
                System.out.println("Choose the movie you wish to see: ");
                for (int i = 0; i < mediaByYearOfRelease.size(); i++) {
                    System.out.println(i + 1 + " - " + mediaByYearOfRelease.get(i).getName());
                }
                String selectedInput = scanner.nextLine();
                int userInput2 = -1;
                try {
                    userInput2 = Integer.parseInt(selectedInput);
                    if (userInput2 > mediaByYearOfRelease.size() || userInput2 <= 0) {
                        System.out.println("The chosen movie doesn't exist, please try again.");
                        searchByYearOfRelease(Media);

                    }

                } catch (Exception e) {
                    System.out.println("The chosen movie doesn't exist, please try again. ");
                    searchByYearOfRelease(Media);
                }
                allMedia.get(userInput2).chooseMedia();

            }

}
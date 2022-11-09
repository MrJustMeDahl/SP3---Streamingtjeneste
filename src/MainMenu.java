import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<AMedia> allMedia = new ArrayList<>();
    private ArrayList<String> allcategories = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public MainMenu(ArrayList<AMedia> allMedia) {
        this.allMedia = allMedia;
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
            if (userInput.equals("1")) {'
            // lav ny liste kun med film
                searchMovies();
            }
            if (userInput.equals("2")) {
                //lav ny liste kun med serier
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
                    searchByMediaName();
                }
                if (userInput.equals("2")) {
                    searchByCategory();
                    }
                if (userInput.equals("3")) {
                   searchByRating();
                }
                if (userInput.equals("4")) {
                    searchByYearOfRelease();
                }
                if (userInput.equals("5")) {
                    searchEngine();
                }
                System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
            }

    }

    private AMedia searchSeries() {
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
               searchByMediaName();
            }
            if (userInput.equals("2")) {
               searchByCategory();

            }
            if (userInput.equals("3")) {
                searchByRating();
            }
            if (userInput.equals("4")) {
                searchByYearOfRelease();
            }
            if (userInput.equals("5")) {
               searchByFinalSeason();
            }
            if (userInput.equals("6")) {
                searchEngine();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }


    }

    private void searchByMediaName(){
        System.out.println(" Movies:\n " + allMedia);
        System.out.println("Please enter the name of the movie you wish to see: ");
        String chosenMovie = scanner.nextLine();
        if (chosenMovie == allMedia.Name()){
            chosenMovie.chooseMedia();
        }
        System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
    }

    private void searchByCategory(){
        System.out.println("Choose a Category you wish to watch: ");
        for(int i = 0; i<allcategories.size(); i++ ){
            System.out.println(i+1+" - "+allcategories.get(i));
        }
        String chooseCategory = scanner.nextLine();
        switch(chooseCategory){
            case "1":
                searchByChosenCategory(allcategories.get(0));
                break;

            case "2":
                searchByChosenCategory(allcategories.get(1));
                break;

            case "3":
                searchByChosenCategory(allcategories.get(2));
                break;

            case "4":
                searchByChosenCategory(allcategories.get(3));
                break;

            case "5":
                searchByChosenCategory(allcategories.get(4));
                break;

            case "6":
                searchByChosenCategory(allcategories.get(5));
                break;

            case "7":
                searchByChosenCategory(allcategories.get(6));
                break;

            case "8":
                searchByChosenCategory(allcategories.get(7));
                break;

            case "9":
                searchByChosenCategory(allcategories.get(8));
                break;

            case "10":
                searchByChosenCategory(allcategories.get(9));
                break;

            case "11":
                searchByChosenCategory(allcategories.get(10));
                break;

            case "12":
                searchByChosenCategory(allcategories.get(11));
                break;

            case "13":
                searchByChosenCategory(allcategories.get(12));
                break;

            case "14":
                searchByChosenCategory(allcategories.get(13));
                break;

            case "15":
                searchByChosenCategory(allcategories.get(14));
                break;

            case "16":
                searchByChosenCategory(allcategories.get(15));
                break;

            case "17":
                searchByChosenCategory(allcategories.get(16));
                break;

            case "18":
                searchByChosenCategory(allcategories.get(17));
                break;

            case "19":
                searchByChosenCategory(allcategories.get(18));
                break;

            default:
                System.out.println("Can't register your answer please try again: ");
                searchByCategory();
        }

    }

    private void searchByChosenCategory(String chooseCategory){




    }

    private void searchByRating(){
        System.out.println("Please enter then minimum rating of movies you wish to see.");
        float r = scanner.nextFloat();
     if (float r <= allMedia){

        }

    }

    private void searchByYearOfRelease(){


    }

    private void searchByFinalSeason(){



    }

}

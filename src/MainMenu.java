import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MainMenu {

    private ArrayList<User> allUsers;
    private ArrayList<AMedia> allMedia;
    private ArrayList<AMedia> allMovies;
    private ArrayList<AMedia> allSeries;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);
    public MainMenu(ArrayList<AMedia> allMedia, ArrayList<User> allUsers, ArrayList<AMedia> allMovies, ArrayList<AMedia> allSeries){
        this.allMedia = allMedia;
        this.allUsers = allUsers;
        this.allMovies = allMovies;
        this.allSeries = allSeries;
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
                    //searchEngine();
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

    public void userOptions(){
        System.out.println("You have these options to alter your user settings:");
        System.out.println("1 - Change username.");
        System.out.println("2 - Change password.");
        System.out.println("3 - Delete account.");
        System.out.println("4 - Return to main menu.");
        String userInput = scanner.nextLine();
        switch(userInput){
            case "1":
                System.out.println("Please enter your new username or press B go back: ");
                String username = scanner.nextLine();
                if(username.equalsIgnoreCase("b")){
                    userOptions();
                }
                ProgramControl.currentUser.setUsername(username);
                break;
            case "2":
                System.out.println("Please enter your new username or press B go back: ");
                String password = scanner.nextLine();
                if(password.equalsIgnoreCase("b")){
                    userOptions();
                }
                ProgramControl.currentUser.setPassword(password);
                break;
            case "3":
                System.out.println("Are you sure that you want to delete your account?");
                System.out.println("Press y to delete your account - this can not be undone.");
                System.out.println("Press any other key to go back.");
                String userChoice = scanner.nextLine();
                if(userChoice.equalsIgnoreCase("y")){
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
}
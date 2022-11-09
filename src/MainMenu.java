import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<User> allUsers;
    private ArrayList<AMedia> allMedia;
    private Random rnd = new Random();
    private Scanner scanner = new Scanner(System.in);
    public MainMenu(ArrayList<AMedia> allMedia, ArrayList<User> allUsers){
        this.allMedia = allMedia;
        this.allUsers = allUsers;

    }

    public void runMainMenu(){

        System.out.println("Welcome " + ProgramControl.currentUser.getUsername() + ", please enter an option below.");
        while(true) {
            String userInput = scanner.nextLine();
            System.out.println("1 - Get suggestions.");
            if (userInput.equals("1")) {
                suggestedMedia();
            }
            System.out.println("2 - Search.");
            if (userInput.equals("2")) {
                searchEngine();
            }
            System.out.println("3 - log out.");
            if (userInput.equals("3")) {
                logOut();
            }
            System.out.println("The option you have chosen does not exist.\n" + "Please try again: ");
        }
    }

    private void suggestedMedia() {
        AMedia suggestion1 = allMedia.get(rnd.nextInt(0, allMedia.size()));
        AMedia suggestion2 = allMedia.get(rnd.nextInt(0, allMedia.size()));
        AMedia suggestion3 = allMedia.get(rnd.nextInt(0, allMedia.size()));
            if(ProgramControl.currentUser.getWatchedMedia().size() > 0) {
                //Finding the last played media from the current user.
                AMedia lastPlayedMedia = ProgramControl.currentUser.getWatchedMedia().get(ProgramControl.currentUser.getWatchedMedia().size() - 1);
                //Finding the category/categories of the last played media and picking a random category between them.
                String lastPlayedCategory = lastPlayedMedia.getCategory();
                String[] lastPlayedCategories = lastPlayedCategory.split(", ");


                String chosenCategory = getRandomCategory(lastPlayedCategories);
                //Creating a list of media that has the same category as the randomly chosen categories, and picking a random media from the list. Do this for all 3 randomly chosen categories.
                suggestion1 = getRandomMediaFromCategory(chosenCategory);
                chosenCategory = getRandomCategory(lastPlayedCategories);
                suggestion2 = getRandomMediaFromCategory(chosenCategory);
                chosenCategory = getRandomCategory(lastPlayedCategories);
                suggestion3 = getRandomMediaFromCategory(chosenCategory);
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
    private AMedia getRandomMediaFromCategory(String category){
        ArrayList<AMedia> listOfMediaFromCategory = new ArrayList<>();
        for(AMedia m: allMedia) {
            if(m.getCategory().contains(category)){
                listOfMediaFromCategory.add(m);
            }
        }
        int randomMediaFromList = rnd.nextInt(0, listOfMediaFromCategory.size());
        return listOfMediaFromCategory.get(randomMediaFromList);
    }

    private String getRandomCategory(String[] lastPlayedCategories){
        int randomCategory = rnd.nextInt(0,lastPlayedCategories.length);
        return lastPlayedCategories[randomCategory];
    }
    public void logOut() {

        FileHandling.writeToUserFile("Data/UserData.txt", allUsers);
            System.out.println("We are looking forward to see you again!");
            try{
                wait(1500);

            }catch (InterruptedException e){

            }

        ProgramControl pc = new ProgramControl();
        pc.runProgram();

    }
}

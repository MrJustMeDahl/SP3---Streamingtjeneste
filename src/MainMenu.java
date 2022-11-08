import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<AMedia> allMedia = new ArrayList<AMedia>();
    Random rnd = new Random();
    Scanner scanner = new Scanner(System.in);
    public MainMenu(ArrayList<AMedia> allMedia){
        this.allMedia = allMedia;
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

    private void suggestedMedia(){
        //Finding the last played media from the current user.
        AMedia lastPlayedMedia = ProgramControl.currentUser.getWatchedMedia().get(ProgramControl.currentUser.getWatchedMedia().size()-1);
        //Finding the category/categories of the last played media and picking a random category between them.
        String lastPlayedCategory = lastPlayedMedia.getCategory();
        String[] lastPlayedCategories = lastPlayedCategory.split(", ");

        String chosenCategory = getRandomCategory(lastPlayedCategories);
        //Creating a list of media that has the same category as the randomly chosen categories, and picking a random media from the list. Do this for all 3 randomly chosen categories.
        AMedia suggestion1 = getRandomMediaFromCategory(chosenCategory);
        chosenCategory = getRandomCategory(lastPlayedCategories);
        AMedia suggestion2 = getRandomMediaFromCategory(chosenCategory);
        chosenCategory = getRandomCategory(lastPlayedCategories);
        AMedia suggestion3 = getRandomMediaFromCategory(chosenCategory);

        System.out.println("We have found these options you might like: ");
        System.out.println("1 - " + suggestion1.getName() + ".");
        System.out.println("2 - " + suggestion2.getName() + ".");
        System.out.println("3 - " + suggestion3.getName() + ".");
        System.out.println("4 - Go back to main menu." );
        String userInput = scanner.nextLine().trim();
        if(userInput.equals("1")){
            suggestion1.chooseMedia();
        } else if(userInput.equals("2")){
            suggestion2.chooseMedia();
        } else if(userInput.equals("3")){
            suggestion3.chooseMedia();
        } else if(userInput.equals("4")){
            runMainMenu();
        } else {
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
        String chosenCategory = lastPlayedCategories[randomCategory];
        return chosenCategory;
    }

}

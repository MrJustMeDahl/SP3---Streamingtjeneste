import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<AMedia> allMedia = new ArrayList<AMedia>();

    public MainMenu(ArrayList<AMedia> allMedia){
        this.allMedia = allMedia;
    }

    public void runMainMenu(){
        Scanner scanner = new Scanner(System.in);
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
        AMedia lastPlayedMedia = ProgramControl.currentUser.getWatchedMedia().get(ProgramControl.currentUser.getWatchedMedia().size()-1);
        String lastPlayedCategory = lastPlayedMedia.getCategory();
        String[] lastPlayedCategories = lastPlayedCategory.split(", ");
        Random rnd = new Random();
        int randomCategory = rnd.nextInt(0,lastPlayedCategories.length);
        String chosenCategoryOne = lastPlayedCategories[randomCategory];
        randomCategory = rnd.nextInt(0,lastPlayedCategories.length);
        String chosenCategoryTwo = lastPlayedCategories[randomCategory];
        randomCategory = rnd.nextInt(0,lastPlayedCategories.length);
        String chosenCategoryThree = lastPlayedCategories[randomCategory];
        ArrayList<AMedia> listOfMediaFromCategory = new ArrayList<>();
        for(AMedia m: allMedia) {
            if(m.getCategory().contains(chosenCategoryOne)){
                listOfMediaFromCategory.add(m);
            }
        }
        int randomMediaFromList = rnd.nextInt(0, listOfMediaFromCategory.size());
        AMedia suggestion1 = listOfMediaFromCategory.get(randomMediaFromList);
        System.out.println("We have found these options you might like: ");
        System.out.println("1 - " + suggestion1);
        

    }

}

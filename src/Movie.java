import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Movie extends AMedia {

    Scanner sc = new Scanner(System.in);
    Movie(String name, String category, float rating, int releaseYear) {
        super(name, category, rating, releaseYear);
    }

    @Override
    protected void playMedia(AMedia media) {
        if (ProgramControl.currentUser.getSavedMedia().contains(media)){
            ProgramControl.currentUser.removeSavedMedia(media);
        }
        ProgramControl.currentUser.addWatchedMedia(media);
        System.out.println("You are now watching : " + name);
        try {
            sleep(2500);
        } catch(Exception e){
        }
        System.out.println(name + " is now done. Hopefully you enjoyed it.");
        System.out.println("Returning to main menu.");
        try{
            sleep(2500);
        }catch (Exception e){
        }
        for(int i = 0; i < 25; i++){
            System.out.println("\n");
        }
    }

    }



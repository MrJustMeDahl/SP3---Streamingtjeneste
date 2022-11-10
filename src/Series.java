import org.w3c.dom.ls.LSOutput;

import javax.naming.Name;

import static java.lang.Thread.sleep;

public class Series extends AMedia{

    private int episode;
    private int season;
    private int endYear;
    public Series(String name, String category, float rating, int releaseYear, int season, int episode, int endYear) {

        super(name, category, rating, releaseYear);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;
        super.name += season + "-" + episode;
    }


    public int getEpisode() {
        return episode;
    }

    public int getSeason() {
        return season;
    }

    public int getEndYear() {
        return endYear;
    }

    @Override
    protected void playMedia(AMedia media) {
        if (ProgramControl.currentUser.getSavedMedia().contains(media)){
            ProgramControl.currentUser.removeSavedMedia(media);
        }
        if(!ProgramControl.currentUser.getWatchedMedia().contains(media)){
            ProgramControl.currentUser.addWatchedMedia(media);
        }
        System.out.println("You are now watching : " + name + "Episode : " + episode);
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

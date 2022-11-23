import static java.lang.Thread.sleep;

public class Series extends AMedia{

    private int episode;
    private int season;
    private int endYear;
    private String databaseName;
    public Series(String name, String category, float rating, int releaseYear, int season, int episode, int endYear) {

        super(name, category, rating, releaseYear);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;
        this.databaseName = super.name;
        super.name += " " + season + "-" + episode;
    }

    public String getDatabaseName(){
        return databaseName;
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
            if (ProgramControl.usingDatabase) {
                DatabaseHandling database = (DatabaseHandling) ProgramControl.dataHandling;
                database.removeSavedSeries(this);
            }
        }
        if(!ProgramControl.currentUser.getWatchedMedia().contains(media)){
            ProgramControl.currentUser.addWatchedMedia(media);
            if (ProgramControl.usingDatabase) {
                DatabaseHandling database = (DatabaseHandling) ProgramControl.dataHandling;
                database.addWatchedSeries(this);
            }
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

public class Movie extends AMedia {

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

    }
}

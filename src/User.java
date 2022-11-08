import java.util.ArrayList;

public class User {

    private String Username;
    private String Password;
    private int age;
    ArrayList<AMedia>watchedMedia;
    ArrayList<AMedia>savedMedia;


    public User(String username, String password, int age, ArrayList<AMedia> watchedMedia, ArrayList<AMedia> savedMedia) {
        Username = username;
        Password = password;
        this.age = age;
        this.watchedMedia = watchedMedia;
        this.savedMedia = savedMedia;
    }


    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public int getAge() {
        return age;

    }

    public ArrayList<AMedia> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<AMedia> getSavedMedia() {
        return savedMedia;
    }

    public void addSavedMedia(AMedia media){
        savedMedia.add(media);
    }

    public void removeSavedMedia(AMedia media){
        savedMedia.remove(media);
    }

    public void addWatchedMedia(AMedia media){
        watchedMedia.add(media);
    }

}

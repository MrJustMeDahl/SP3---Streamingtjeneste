import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private int age;
    ArrayList<AMedia>watchedMedia;
    ArrayList<AMedia>savedMedia;


    public User(String username, String password, int age, ArrayList<AMedia> watchedMedia, ArrayList<AMedia> savedMedia) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.watchedMedia = watchedMedia;
        this.savedMedia = savedMedia;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
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

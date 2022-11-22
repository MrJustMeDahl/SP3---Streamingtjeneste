import java.util.ArrayList;

public interface DataHandling {

    public ArrayList<AMedia> readMovieData(String path);
    public ArrayList<AMedia> readSeriesData(String path);
    public ArrayList<User> readUserData(String path, ArrayList<AMedia> allMedia);
    public void writeUserData (String path, ArrayList<User> allUsers);
    public ArrayList<String> readFromCategoryFile(String path);

}

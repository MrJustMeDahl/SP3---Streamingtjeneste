import java.util.ArrayList;

public interface DataHandling {

    ArrayList<AMedia> readFromMovieFile(String path);
    ArrayList<AMedia> readFromSeriesFile(String path);
    ArrayList<User> readFromUserFile(String path, ArrayList<AMedia> allMedia);
    void writeToUserFile(String path, ArrayList<User> allUsers);
    ArrayList<String> readFromCategoryFile(String path);

}

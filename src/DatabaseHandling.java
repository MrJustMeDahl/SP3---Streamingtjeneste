import java.util.ArrayList;

public class DatabaseHandling implements DataHandling{
    @Override
    public ArrayList<AMedia> readMovieData(String path) {
        return null;
    }

    @Override
    public ArrayList<AMedia> readSeriesData(String path) {
        return null;
    }

    @Override
    public ArrayList<User> readUserData(String path, ArrayList<AMedia> allMedia) {
        return null;
    }

    @Override
    public void writeUserData(String path, ArrayList<User> allUsers) {

    }

    @Override
    public ArrayList<String> readFromCategoryFile(String path) {
        return null;
    }
}

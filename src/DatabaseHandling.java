import java.util.ArrayList;
import java.sql.*;

public class DatabaseHandling implements DataHandling{

    public Connection connection;
    private String url = "jdbc:mysql://localhost/mediadata?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "cph-nr135";
    public boolean isDatabaseOnline = true;

    public DatabaseHandling(){
        establishConnection();
    }

    public void establishConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database. Loading data from local files.");
            this.isDatabaseOnline = false;
        }
    }

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

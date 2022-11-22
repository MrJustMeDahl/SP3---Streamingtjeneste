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

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void establishConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database. Loading data from local files.");
            this.isDatabaseOnline = false;
        }
    }

    @Override
    public ArrayList<AMedia> readMovieData(String path) {
        ArrayList<AMedia> mediaFromDatabase = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String movieName = result.getString("MovieName");
                int movieReleaseYear = result.getInt("MovieReleaseYear");
                String movieCategory = result.getString("MovieCategory");
                String movieRating = result.getString("MovieRating");
                float movieRatingProcessed = Float.parseFloat(movieRating.replace(",", "."));
                Movie movie = new Movie(movieName, movieCategory, movieRatingProcessed, movieReleaseYear);
                mediaFromDatabase.add(movie);
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
        return mediaFromDatabase;
    }

    @Override
    public ArrayList<AMedia> readSeriesData(String path) {
        establishConnection();
        ArrayList<AMedia> mediaFromDatabase = new ArrayList<>();
        String query = "SELECT * FROM series";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String seriesName = result.getString("SeriesName");
                String seriesCategory = result.getString("SeriesCategory");
                String seriesRating = result.getString("SeriesRating");
                float seriesRatingProcessed = Float.parseFloat(seriesRating.replaceAll(",", "."));
                String seriesYear = result.getString("SeriesReleaseYear");
                String[] seriesYearDivided = seriesYear.trim().split("-");
                int seriesReleaseYear = Integer.parseInt(seriesYearDivided[0]);
                int seriesEndYear = 0;
                if(seriesYearDivided.length < 1) {
                    seriesEndYear = Integer.parseInt(seriesYearDivided[1]);
                }
                String seriesSeasons = result.getString("SeriesSeasons");
                String[] seasonsDivided = seriesSeasons.split(", ");
                for (int i = 0; i < seasonsDivided.length; i++) {
                    String[] episodesPerSeason = seasonsDivided[i].split("-");
                    int seasonNumber = Integer.parseInt(episodesPerSeason[0].trim());
                    int numberOfEpisodes = Integer.parseInt(episodesPerSeason[1].trim());
                    for(int episode = 1; episode <= numberOfEpisodes; episode++) {
                        Series series = new Series(seriesName, seriesCategory, seriesRatingProcessed, seriesReleaseYear, seasonNumber, episode, seriesEndYear);
                        mediaFromDatabase.add(series);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
        return mediaFromDatabase;
    }

    @Override
    public ArrayList<User> readUserData(String path, ArrayList<AMedia> allMedia) {
        establishConnection();
        closeConnection();
        return null;
    }

    @Override
    public void writeUserData(String path, ArrayList<User> allUsers) {
        establishConnection();
        closeConnection();
    }

    @Override
    public ArrayList<String> readFromCategoryFile(String path) {
        establishConnection();
        closeConnection();
        return null;
    }
}

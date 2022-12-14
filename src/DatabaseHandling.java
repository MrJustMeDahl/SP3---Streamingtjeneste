import java.util.ArrayList;
import java.sql.*;

public class DatabaseHandling implements DataHandling{

    public Connection connection;
    private String url = "jdbc:mysql://localhost/mediadata?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "cph-nr135";
    public boolean isDatabaseOnline = false;

    public DatabaseHandling(){
        establishConnection();
    }

    public void closeConnection(){
        try {
            connection.close();
            this.isDatabaseOnline = false;
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void establishConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            this.isDatabaseOnline = true;
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
        ArrayList<User> usersFromData = new ArrayList<>();
        String query1 = "SELECT * FROM users";
        String query2 = "SELECT * FROM watchedmovies JOIN movies ON watchedmovies.MovieID = movies.MovieID WHERE UserID = ?";
        String query3 = "SELECT * FROM watchedseries JOIN series ON watchedseries.SeriesID = series.SeriesID WHERE UserID = ?";
        String query4 = "SELECT * FROM savedmovies JOIN movies ON savedmovies.MovieID = movies.MovieID WHERE UserID = ?";
        String query5 = "SELECT * FROM savedseries JOIN series ON savedseries.SeriesID = series.SeriesID WHERE UserID = ?";
        try{
            PreparedStatement statement1 = connection.prepareStatement(query1);
            PreparedStatement statement2 = connection.prepareStatement(query2);
            PreparedStatement statement3 = connection.prepareStatement(query3);
            PreparedStatement statement4 = connection.prepareStatement(query4);
            PreparedStatement statement5 = connection.prepareStatement(query5);
            ResultSet result1 = statement1.executeQuery();
            while(result1.next()) {
                String userID = result1.getString("UserID");
                String username = result1.getString("Username");
                String password = result1.getString("Password");
                int age = result1.getInt("Age");
                ArrayList<AMedia> watchedMedia = new ArrayList<>();
                statement2.setString(1, userID);
                ResultSet result2 = statement2.executeQuery();
                while(result2.next()){
                    for(AMedia m: allMedia){
                        if(result2.getString("MovieName").equals(m.getName())){
                            watchedMedia.add(m);
                        }
                    }
                }
                statement3.setString(1, userID);
                ResultSet result3 = statement3.executeQuery();
                while(result3.next()){
                    for(AMedia m: allMedia){
                        if(m.getName().contains(result3.getString("SeriesName"))){
                            watchedMedia.add(m);
                        }
                    }
                }
                ArrayList<AMedia> savedMedia = new ArrayList<>();
                statement4.setString(1, userID);
                ResultSet result4 = statement4.executeQuery();
                while(result4.next()){
                    for(AMedia m: allMedia){
                        if(result4.getString("MovieName").equals(m.getName())) {
                            savedMedia.add(m);
                        }
                    }
                }
                statement5.setString(1, userID);
                ResultSet result5 = statement5.executeQuery();
                while(result5.next()){
                    for(AMedia m: allMedia){
                        if(m.getName().contains(result5.getString("SeriesName"))) {
                            savedMedia.add(m);
                        }
                    }
                }
                User user = new User(username, password, age, watchedMedia, savedMedia);
                usersFromData.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
        return usersFromData;
    }

    @Override
    public void writeUserData(String path, ArrayList<User> allUsers) {
        establishConnection();
        String clearTable = "TRUNCATE users";
        String insertUser = "INSERT INTO users (Username, Password, Age) VALUES (?, ?, ?)";
        try {
            PreparedStatement clearStatement = connection.prepareStatement(clearTable);
            clearStatement.execute();
            PreparedStatement insertStatement = connection.prepareStatement(insertUser);
            for(User u: allUsers){
                insertStatement.setString(1, u.getUsername());
                insertStatement.setString(2, u.getPassword());
                insertStatement.setString(3, "" + u.getAge());
                insertStatement.execute();
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    @Override
    public ArrayList<String> readCategoryData(String path) {
        establishConnection();
        ArrayList<String> categoriesFromData = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                categoriesFromData.add(result.getString("CategoryName"));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
        return categoriesFromData;
    }

    public void addSavedMovie(AMedia media){
        establishConnection();
        String insertQuery = "INSERT INTO savedmovies (UserID, MovieID) VALUES (?, ?)";
        try{
            int movieID = findMovieID(media);
            int userID = findUserID();
            PreparedStatement insertSavedMovie = connection.prepareStatement(insertQuery);
            insertSavedMovie.setString(1, "" + userID);
            insertSavedMovie.setString(2, "" + movieID);
            insertSavedMovie.execute();
        } catch(SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    public void addSavedSeries(AMedia media){
        establishConnection();
        String insertQuery = "INSERT INTO savedseries (UserID, SeriesID) VALUES (?, ?)";
        try{
            int seriesID = findSeriesID(media);
            int userID = findUserID();
            PreparedStatement insertSavedMovie = connection.prepareStatement(insertQuery);
            insertSavedMovie.setString(1, "" + userID);
            insertSavedMovie.setString(2, "" + seriesID);
            insertSavedMovie.execute();
        } catch(SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    public void removeSavedMovie(AMedia media){
        establishConnection();
        String deleteQuery = "DELETE FROM savedmovies WHERE savedmovies.UserID = ? AND savedmovies.MovieID = ?";
        try{
            int movieID = findMovieID(media);
            int userID = findUserID();
            PreparedStatement deleteSavedMovie = connection.prepareStatement(deleteQuery);
            deleteSavedMovie.setString(1, "" + userID);
            deleteSavedMovie.setString(2, "" + movieID);
            deleteSavedMovie.execute();
        } catch(SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    public void removeSavedSeries(AMedia media){
        establishConnection();
        String deleteQuery = "DELETE FROM savedseries WHERE savedseries.UserID = ? AND savedseries.seriesID = ?";
        try{
            int seriesID = findSeriesID(media);
            int userID = findUserID();
            PreparedStatement deleteSavedMovie = connection.prepareStatement(deleteQuery);
            deleteSavedMovie.setString(1, "" + userID);
            deleteSavedMovie.setString(2, "" + seriesID);
            deleteSavedMovie.execute();
        } catch(SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    public void addWatchedMovie(AMedia media){
        establishConnection();
        String insertQuery = "INSERT INTO watchedmovies (UserID, MovieID) VALUES (?, ?)";
        try{
            int movieID = findMovieID(media);
            int userID = findUserID();
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, "" + userID);
            insertStatement.setString(2, "" + movieID);
            insertStatement.execute();
        } catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    public void addWatchedSeries(AMedia media){
        establishConnection();
        String insertQuery = "INSERT INTO watchedseries (UserID, SeriesID) VALUES (?, ?)";
        try{
            int seriesID = findSeriesID(media);
            int userID = findUserID();
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, "" + userID);
            insertStatement.setString(2, "" + seriesID);
            insertStatement.execute();
        } catch (SQLException e){
            throw new RuntimeException();
        }
        closeConnection();
    }

    private int findMovieID(AMedia media){
        String movieQuery = "SELECT * FROM movies WHERE movies.MovieName = ?";
        int movieID = 0;
        try {
            PreparedStatement identifyMovie = connection.prepareStatement(movieQuery);
            identifyMovie.setString(1, media.getName());
            ResultSet movieResult = identifyMovie.executeQuery();

            while (movieResult.next()) {
                movieID = movieResult.getInt("MovieID");
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return movieID;
    }

    private int findSeriesID(AMedia media){
        String seriesQuery = "SELECT * FROM series WHERE series.SeriesName = ?";
        Series series = (Series) media;
        int seriesID = 0;
        try {
            PreparedStatement identifySeries = connection.prepareStatement(seriesQuery);
            identifySeries.setString(1, series.getDatabaseName());
            ResultSet seriesResult = identifySeries.executeQuery();
            while (seriesResult.next()) {
                seriesID = seriesResult.getInt("SeriesID");
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return seriesID;
    }

    private int findUserID(){
        String userQuery = "SELECT * FROM users WHERE users.Username = ?";
        int userID = 0;
        try {
            PreparedStatement identifyUser = connection.prepareStatement(userQuery);
            identifyUser.setString(1, ProgramControl.currentUser.getUsername());
            ResultSet userResult = identifyUser.executeQuery();
            while (userResult.next()) {
                userID = userResult.getInt("UserID");
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return userID;
    }
}

public class Series extends AMedia{

    int episode;
    int season;
    Series(String name, String category, float rating, int releaseYear, int season, int episode) {

        super("name", "category", rating, releaseYear, season, episode);
    }


}

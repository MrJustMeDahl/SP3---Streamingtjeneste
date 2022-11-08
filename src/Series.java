public class Series extends AMedia{

    int episode;
    int season;
    int endYear;
    Series(String name, String category, float rating, int releaseYear, int season, int episode, int endYear) {

        super(name, category, rating, releaseYear);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;

    }


}

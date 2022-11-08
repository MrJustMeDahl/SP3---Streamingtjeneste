public abstract class AMedia {

    protected String name;
    protected String category;
    protected float rating;
    protected int releaseYear;

    AMedia(String name, String category, float rating, int releaseYear){
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.releaseYear = releaseYear;

    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    abstract protected void playMedia(AMedia media);


}

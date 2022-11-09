import java.util.Scanner;

public abstract class AMedia {

    Scanner sc = new Scanner(System.in);
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
    public void chooseMedia() {

        System.out.println("Choose an option below  : ");
        System.out.println("Press 1 if you want to play this : ");
        System.out.println("press 2 if you want to save this : ");
        System.out.println("Press 3 to run main menu.");
        String userInput = sc.nextLine().trim();

        switch (userInput) {
            case "1":
                playMedia(this);
                break;
            case "2":
                ProgramControl.currentUser.addSavedMedia(this);
                break;
            case "3":
                ProgramControl.mainMenu.runMainMenu();
            default:
                System.out.println("The button you pressed is not valid, please try again!");
                chooseMedia();

        }
    }

    }

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
        System.out.println("You have chosen: " + name + "\n");
        System.out.println("Choose an option below: ");
        System.out.println("1 - if you want to play this: ");
        if(ProgramControl.currentUser.getSavedMedia().contains(this)){
            System.out.println("2 - if you want to remove this from your list: \n");
        } else {
            System.out.println("2 - if you want to save this: \n");
        }
        System.out.println("3 - Back to main menu.");
        String userInput = sc.nextLine().trim();

        switch (userInput) {
            case "1":
                playMedia(this);
                ProgramControl.mainMenu.runMainMenu();
                break;
            case "2":
                if(ProgramControl.currentUser.getSavedMedia().contains(this)){
                    ProgramControl.currentUser.removeSavedMedia(this);
                } else {
                    ProgramControl.currentUser.addSavedMedia(this);
                }
                ProgramControl.mainMenu.runMainMenu();
                break;
            case "3":
                ProgramControl.mainMenu.runMainMenu();
                break;
            default:
                System.out.println("The button you pressed is not valid, please try again!");
                chooseMedia();

        }
    }

    }

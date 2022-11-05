import java.util.ArrayList;
import java.util.Scanner;

public class StartMenu {
  private static final Scanner scanner = new Scanner(System.in);
  String currentUser;

public String StartMenu(ArrayList<User> AllUsers){

}

//StartMenu Choose to login or create new user
public String runStartMenu(String currentUser){
  System.out.println("1 - Login");
  System.out.println("2 - Create new user");
  while (true){
    String answer = scanner.nextLine();
    if ("1".equals(answer)){
      return this.login();
    }
    if ("2".equals (answer)){
      return this.createNewUser();
    }
    System.out.println("The key you have pressed doesn't exist");
    System.out.println("Try again: ");
  }
  ProgramControl.setCurrentUser = currentUser;
}

//Set user, else return to loginscreen
private String login(){
  System.out.println("Please enter your Username:");
  System.out.println("or press 'q' to quit");
  String name = scanner.nextLine();
  if (name != ProgramControl.allUsers){
    System.out.println("This User doesn't exists. ");
  }
  else if (name.equalsIgnoreCase("q")) {
    runStartMenu(currentUser);
  }
  System.out.println("Please enter your Password: ");
  System.out.println("or press 'q' to quit");
  String Password = scanner.nextLine();
  if (Password != ProgramControl.allUsers){
    System.out.println("you have entered the wrong Password: ");
  }
  else if (name.equalsIgnoreCase("q")) {
    runStartMenu(currentUser);
  }
return currentUser;

}
//Create a new user
private String createNewUser(String aNewUser){
  System.out.println("Please enter a Username: ");
  String userName = scanner.nextLine();
  System.out.println("Please enter a Password: ");
  String passWord = scanner.nextLine();
  System.out.println("Please enter your age: ");
  int age = scanner.nextInt();

  return aNewUser;
  ProgramControl.allUsers.add(aNewUser);
}

private String checkUserData(){


}

private String saveUserData(){


}

}

import java.util.ArrayList;
import java.util.Scanner;


public class StartMenu {
ArrayList<User> allUsers = new ArrayList<>();
Scanner scanner = new Scanner(System.in);

public StartMenu(ArrayList<User> allUsers){
this.allUsers = allUsers;


}
//******
//StartMenu Choose to login or create new user
//******
public User runStartMenu(){
    System.out.println("Welcome to Everything Media.");
    System.out.println("");
    System.out.println("To choose options throughout the entire program. \n" +
            "You will need to type ONE of the Numbers. \n" +
            "Then hit ENTER to choose the desired option!!");
    System.out.println("");
    System.out.println("What would you like to do?");
  System.out.println("1 - Login");
  System.out.println("2 - Create new user");
  while (true){
    String answer = scanner.nextLine();
    if ("1".equals(answer)){
      return login();
    }
    if ("2".equals (answer)){
      return createNewUser();
    }
    System.out.println("The key you have pressed doesn't exist");
    System.out.println("Try again: ");
  }
}
//******
//Set user, or return to loginscreen
//******
private User login(){
  System.out.println("Please enter your Username:");
  System.out.println("or press 'q' to quit");
  String name = scanner.nextLine();
 for(User u:allUsers){
   if (u.getUsername().equals(name) ) {
     System.out.println("Please enter your Password: ");
     String Password = scanner.nextLine();
     if (Password.equalsIgnoreCase("q")) {
       name = "q";
       break;
     }
     if (u.getPassword().equals(Password)){
       return u;
     }
     System.out.println("you have entered an invalid Password: ");
   }

 }
  if (name.equalsIgnoreCase("q")) {
  runStartMenu();
  }
  System.out.println("This User doesn't exists. ");
 User dummyuser = runStartMenu();

return dummyuser;
}


//******
//Create a new user
//******
private User createNewUser(){
   int age = -1;
   String userName;
   String password;

   while (true) {
     System.out.println("Please enter a Username: ");
     userName = scanner.nextLine();
     System.out.println("Please enter a Password: ");
     password = scanner.nextLine();
     System.out.println("Please enter your age: ");
     try {
       age = Integer.parseInt(scanner.nextLine());
       if (age != -1){
         break;
       }
     } catch (Exception e) {
       System.out.println("Age not registered");

     }
     }
     return new User(userName, password, age, new ArrayList<AMedia>(), new ArrayList<AMedia>());

   }
}

import java.sql.Connection;
import java.util.ArrayList;

public class dataBaseCon {
    private Connection connection;

    static void DataBaseConn(){
        String url = " " + "autoReconnect=true&useSSL=false";
        String username = "root";
        String Password = "KETNESJtz88G5Q";
        ArrayList<userData> userDtb = new ArrayList<userData>();

    }
    public void run(){
        //connection
        establishConnection();

        //statement
        String query = "";
    }
}

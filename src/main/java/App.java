import java.sql.Connection;
import java.sql.SQLException;



public class App {

    public static void main(String[] args) throws SQLException, InterruptedException {
        String url = "jdbc:h2:~/test";
        String user = "admin";
        String passwd = "admin";

        AppLogger.infoOut("App Started!");

        ConnectionUtils connectionDriver = new ConnectionUtils(url, user, passwd);
        Connection newConnection = connectionDriver.createConnection();

        connectionDriver.closeConnection(newConnection);

        AppLogger.infoOut("App Exited!");
    }


}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import AppLogger;

public class ConnectionUtils {

    private Connection connection;
    private PreparedStatement sqlStatement;

    private String conUrl;
    private String username;
    private String pwd;

    private String query_string;

    public ConnectionUtils(String conUrl, String username, String pwd) {
        this.conUrl = conUrl;
        this.username = username;
        this.pwd = pwd;
    }
    
    public void setQuery_string(String SQLCODE) throws SQLException {
        this.query_string = SQLCODE;
        this.sqlStatement = connection.prepareStatement(SQLCODE);
        AppLogger.infoOut("Statement created with sql string -> " + SQLCODE);
    }

    public Connection createConnection() throws SQLException {
        connection = DriverManager.getConnection(conUrl, username, pwd);
        String conName = "Driver:" + connection.getClass().getName() + " Connection:~" + connection.getCatalog();
        AppLogger.infoOut(conName + " created!");
        return connection;
    }

    public int executeQuery() throws SQLException {
        return sqlStatement.executeUpdate();
    }

    public void closeConnection(Connection con) throws SQLException {
        String conName = "Driver:" + con.getClass().getName() + " Connection:~" + con.getCatalog();
        con.close();

        AppLogger.infoOut(conName + " closed!");
    }
}

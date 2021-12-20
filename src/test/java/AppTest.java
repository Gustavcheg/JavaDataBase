import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private static Connection connection;

    @BeforeEach
    public void init() throws SQLException{
        connection = getNewConnection();
    }

    @AfterEach
    public void close() throws SQLException{
        connection.close();
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:~/test";
        String user = "admin";
        String passwd = "admin";
        return DriverManager.getConnection(url, user, passwd);
    }
    private int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    private void createCustomerTable() throws SQLException{
        String customerTableQuery = "CREATE TABLE customers " +
                "(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
        executeUpdate(customerTableQuery);
    }

    private void executeInsert() throws SQLException {
        String customerTableInsertQuery = "INSERT INTO customers " +
                "('SomeText', 17)";
    }


    @Test
    public void shouldGetjdbcConnection() throws SQLException {

        try(Connection connection = getNewConnection()){
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }


    @Test
    public void shouldCreateCustomerTable() throws SQLException {
        try {
            createCustomerTable();
            connection.createStatement().execute("SELECT * FROM customers");
        }
        catch (JdbcSQLSyntaxErrorException ex){
            connection.createStatement().execute("SELECT * FROM customers");
        }
    }

    @Test
    public void shouldSelectData() throws SQLException {
        try{
            createCustomerTable();
        }
        finally {
            String query = "SELECT * FROM customers WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "");
        }
    }

}

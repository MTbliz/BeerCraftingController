package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {

    public static void executeQuery(String query) {
        DbConnector dbConnector = new DbConnector();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnector.connect();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

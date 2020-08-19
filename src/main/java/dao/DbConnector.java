package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static String URL = "jdbc:postgresql://localhost:5432/beercraftingcontroller";

    private static String USER = "postgres";

    private static String PASSWORD = "admin";

    public static Connection connect(){
        Connection connection = null;
        try {
          connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }
}

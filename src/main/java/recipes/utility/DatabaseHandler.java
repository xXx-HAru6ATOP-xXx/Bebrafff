package recipes.nicefood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {
    static Connection dbConnection;

    public DatabaseHandler() {
    }

    public Connection GetDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/recipes";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "root");
        return dbConnection;
    }
}

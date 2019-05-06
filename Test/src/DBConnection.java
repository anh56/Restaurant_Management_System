package RestaurantManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection connection;
    private final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String DATABASE_URL = "jdbc:sqlserver://localhost:1433";
    private final String user = "doanynhi";
    private final String password = "cselaba1604";

    public Connection mkDatabase() throws SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DATABASE_URL, user, password);
        }
        catch(ClassNotFoundException e){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }

}

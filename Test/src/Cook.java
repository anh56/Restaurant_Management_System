package RestaurantManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cook {
    private void viewCook(){
        System.out.println("Cook ID  Cook Name      Schedule");
        try{
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "SELECT * FROM Restaurant.Cook";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String cookID = resultSet.getString("CookID");
                String cookName = resultSet.getString("CookName");
                String schedule = resultSet.getString("Schedule");
                System.out.println(cookID + "   " + cookName + "    " + schedule);
            }
            statement.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}

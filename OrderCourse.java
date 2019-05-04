package RestaurantManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OrderCourse {
    private Order currOrder = new Order();
    private int currOrderID = currOrder.generateOrderID();
    private void chooseCourse(String courseID){
        try{
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "INSERT INTO Restaurant.OrderCourse (OrderID, CourseID) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, currOrderID);
            statement.setString(2, courseID);
            statement.execute();
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    private void deleteChosenCourse(String courseID){
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "DELETE FROM Restaurant.OrderCourse WHERE CourseID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, courseID);
            statement.execute();
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}

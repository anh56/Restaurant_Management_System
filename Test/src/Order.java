package RestaurantManagementSystem;

import java.sql.*;

public class Order {
    private int orderID;
    private String cusID;

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusID() {
        return cusID;
    }

    public int generateOrderID() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "INSERT INTO Restaurant.Order (OrdeerID) VALUES ((SELECT MAX OrderID FROM Restaurant.Order) + 1)";
            PreparedStatement statement = conn.prepareStatement(query);
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            int order = result.getInt(1);
            statement.execute();
            orderID = order;
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    private void viewOrder(){
        System.out.println("OrderID     OrderDescription    OrderPrice  OrderDuration   CusID");
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "SELECT * FROM Restaurant.Order";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int oId = resultSet.getInt(1);
                String oDescription = resultSet.getString(2);
                float oPrice = resultSet.getFloat(3);
                int oDuration = resultSet.getInt(4);
                String cusID = resultSet.getString(5);
                System.out.format("%d       %s      %.2f        %d      %s\n", oId, oDescription, oPrice, oDuration, cusID);
            }
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }


}

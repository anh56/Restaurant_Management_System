package RestaurantManagementSystem;

import java.sql.*;

public class Bill {
    private int billID;

    public void generateBillID() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "INSERT INTO Restaurant.Bill (BillID) VALUES ((SELECT MAX BillID FROM Restaurant.Bill) + 1)";
            PreparedStatement statement = conn.prepareStatement(query);
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            int bill = result.getInt(1);
            statement.execute();
            billID = bill;
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    private void viewBill(){
        System.out.println("BillID  BillDate  BillTotal  Tax     Discount    CusID  OrderID");
        try{
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "SELECT * FROM Restaurant.Bill";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int billID = resultSet.getInt(1);
                Date billDate = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                float tax = resultSet.getFloat(4);
                float discount = resultSet.getFloat(5);
                String cusID = resultSet.getString(6);
                int orderID = resultSet.getInt(7);
                System.out.print(billID + "   " + billDate + "    ");
                System.out.format("%.2f   %.0f    %.0f    %s   %d\n", total, tax, discount, cusID, orderID);

            }
            statement.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}

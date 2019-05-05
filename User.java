package RestaurantManagementSystem;

import java.sql.*;

public class User {
    private String adminUser = "admin";
    private String adminPass = "admin";
    public boolean login(String username, String password){
        String cusID;
        String cusPass;
        if (username.equals(adminUser) && password.equals(adminPass))
            return true;
        else {
            try{
                String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                String url = "jdbc:sqlserver://localhost:1433";
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
                String query = "SELECT * FROM Restaurant.Customer";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    cusID = resultSet.getString("CusID");
                    cusPass = resultSet.getString("CusPassword");
                    if (username.equals(cusID) && cusPass.equals(password))
                        break;
                }
                statement.close();
                return true;
            }
            catch (Exception e){
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean register(String cusName, String cusCardInfo, String cusPass, String reCusPass){
        if (!cusPass.equals(reCusPass))
            return false;
        try{
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, "doanynhi", "cselaba1604");
            String query = "INSERT INTO Restaurant.Customer (CusID, CusName, CusCardID, CusPass) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "ANN061");
            statement.setString(2, cusName);
            statement.setString(3, cusCardInfo);
            statement.setString(4, cusPass);
            statement.execute();
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return true;
    }
}

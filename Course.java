package RestaurantManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel controlPanel;

    public Course(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("MENU");
        mainFrame.setSize(400, 300);
        mainFrame.getContentPane().setBackground(Color.lightGray);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);

        controlPanel = new JLabel();
        controlPanel.setSize(700, 400);
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void showButtonDemo() throws SQLException {
        headerLabel.setText("PDM 100 RESTAURANT MANAGEMENT SYSTEM");
        headerLabel.setFont(new Font(null, Font.BOLD, 24));
        String[] columnNames = {"ID", "Food Name", "Description", "Price"};
        Object[][] data = new Object[100][4];

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        DBConnection dbConnection = new DBConnection();
        try {
            preparedStatement = dbConnection.mkDatabase().prepareStatement("SELECT CourseID, CourseName, CourseDescription, CoursePrice FROM Restaurant.Course");

            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()){
                data[i][0] = resultSet.getString("CourseID");
                data[i][1] = resultSet.getString("CourseName");
                data[i][2] = resultSet.getString("CourseDescription");
                data[i][3] = resultSet.getFloat("CoursePrice");
                i++;
            }
            mainFrame.setVisible(false);
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Error");
            JOptionPane.showMessageDialog(null, "Error!");
        }

        JTable table = new JTable(data, columnNames);
        table.setSize(400, 400);
        table.setVisible(true);
        controlPanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

//    public static void main(String[] args){
//        Course swingControlDemo = new Course();
//        swingControlDemo.showButtonDemo();
//    }
}

package RestaurantManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Frame2new {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public Frame2new(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("PDM 100 RESTAURANT MANAGEMENT SYSTEM");
        mainFrame.setBounds(100, 100, 1000, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

            mainFrame.getContentPane().setBackground(Color.darkGray);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 300);

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 5));

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showButtonDemo(){
        headerLabel.setText("PDM 100 RESTAURANT");
        this.headerLabel.setFont(new Font(null, Font.BOLD, 28));
        headerLabel.setForeground(Color.white);

        JButton course = new JButton("View Courses");
        JButton bill = new JButton("Pay Bill");
        JButton order = new JButton("Order Food");
        JButton choose = new JButton("Choose Item");
        JButton delete = new JButton("Delete Item");

        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course courseInfo = new Course();
                try {
                    courseInfo.showButtonDemo();
                }
                catch (SQLException ex){
                    Logger.getLogger(Frame2new.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        controlPanel.add(course);

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        Frame2new swingControlDemo = new Frame2new();
        swingControlDemo.showButtonDemo();
    }
}

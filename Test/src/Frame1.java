package RestaurantManagementSystem;

import javax.swing.*;
import java.awt.*;

public class Frame1 extends JFrame {
    JLabel idLabel;
    JLabel passLabel;
    JLabel background;
    JLabel headerLabel;
    JLabel devInfo;

    JTextField id;
    JPasswordField pass;

    JButton submit;

    public Frame1(){
        setTitle("PDM 100 RESTAURANT MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //background
        this.background = new JLabel(new ImageIcon("D:\\New folder\\test.jpg"));
        this.init();
        add(background);
        background.setVisible(true);
        this.pack();
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
    }

    public void init(){
            headerLabel = new JLabel();
            this.headerLabel.setText("PDM 100 RESTAURANT");
            this.headerLabel.setBounds(200, 1, 200, 100);
            this.headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
            headerLabel.setForeground(Color.white);
            add(headerLabel);

            idLabel = new JLabel();
            this.idLabel.setText("Username: ");
            this.idLabel.setBounds(150, 110, 100, 50);
            this.idLabel.setFont(new Font(null, Font.BOLD, 18));
            idLabel.setForeground(Color.white);
            add(idLabel);

        passLabel = new JLabel("Password");
            this.passLabel.setBounds(150, 110, 100, 50);
            this.passLabel.setFont(new Font(null, Font.BOLD, 18));
            add(passLabel);

            devInfo = new JLabel();
            this.devInfo.setText("Copyright of this code belongs to - DOAN Y NHI || HO DANG PHUONG NGOC || NGUYEN THE ANH");
            this.devInfo.setBounds(130, 300, 2000, 3);
            this.devInfo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            devInfo.setForeground(Color.white);

            id = new JFormattedTextField();
            this.id.setBounds(300, 120, 200, 3);
            add(id);

            pass = new JPasswordField();
            this.add(pass);
            this.pass.setBounds(300, 120, 200, 3);

            this.id.setVisible(true);
        this.submit = new JButton("Login");
            this.submit.setBounds(400, 230, 100, 25);
            add(submit);
            submit.addActionListener(this::submitActionPerform);
    }

    public void submitActionPerform(java.awt.event.ActionEvent event){
        if(id.getText().equals("admin") && pass.equals("admin")){
//            this.hide();
            Frame2new frameNew = new Frame2new();
            frameNew.showButtonDemo();
        }
        else {
            JOptionPane.showMessageDialog(null,"Invalid Password or Username!");
        }
    }
}
class MyGui{
    public static void main(String[] a){
        Frame1 frame1 = new Frame1();
        frame1.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Create extends JFrame {


    Container c;
    JLabel name,rollno,marks1,marks2,marks3;
    JTextField tname,trollno,tmarks1,tmarks2,tmarks3;
    JButton submit,back;

    void Create(){

        c = getContentPane();
        c.setLayout(null);

        name = new JLabel("Enter Name ");
        tname = new JTextField(10);
        rollno = new JLabel("Enter Roll no. ");
        trollno = new JTextField(10);
        marks1 = new JLabel("Enter marks of 1st subject");
        tmarks1 = new JTextField(10);
        marks2 = new JLabel("Enter marks of 2nd subject");
        tmarks2 = new JTextField(10);
        marks3 = new JLabel("Enter marks of 3rd subject");
        tmarks3 = new JTextField(10);
        submit = new JButton("Submit");
        back = new JButton("Back");

        
        Font f = new Font("Arial", Font.PLAIN, 20);
        name.setFont(f);
        tname.setFont(f);
        rollno.setFont(f);
        trollno.setFont(f);
        marks1.setFont(f);
        tmarks1.setFont(f);
        marks2.setFont(f);
        tmarks2.setFont(f);
        marks3.setFont(f);
        tmarks3.setFont(f);
        submit.setFont(f);
        back.setFont(f);
    
        
        name.setBounds(100,10,400,40);
        tname.setBounds(100,50,400,40);
        rollno.setBounds(100,100,400,40);
        trollno.setBounds(100,140,400,40);
        marks1.setBounds(100,190,400,40);
        tmarks1.setBounds(100,230,400,40);
        marks2.setBounds(100,280,400,40);
        tmarks2.setBounds(100,320,400,40);
        marks3.setBounds(100,370,400,40);
        tmarks3.setBounds(100,410,400,40);
        submit.setBounds(200, 450, 200, 40);
        back.setBounds(200, 500, 200, 40);


        c.add(name);
        c.add(tname);
        c.add(rollno);
        c.add(trollno);
        c.add(marks1);
        c.add(tmarks1);
        c.add(marks2);
        c.add(tmarks2);
        c.add(marks3);
        c.add(tmarks3);
        c.add(submit);
        c.add(back);


        ActionListener a = (ae) -> {
            sms ap = new sms();
        };
        back.addActionListener(a);

        ActionListener b = (ae) -> {
            
            String name = tname.getText().trim();
            int rollno = Integer.parseInt(trollno.getText().trim());
            int marks1 = Integer.parseInt(tmarks1.getText().trim());
            int marks2 = Integer.parseInt(tmarks3.getText().trim());
            int marks3 = Integer.parseInt(tmarks3.getText().trim());


            if (name.isEmpty() || !name.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(c, "Enter a correct name.");
                tname.setText("");
                tname.requestFocus();
                return;
            }

        };
        submit.addActionListener(b);

        setTitle("Create Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

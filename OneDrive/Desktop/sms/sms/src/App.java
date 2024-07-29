import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class sms extends JFrame {

    Container c;
    JButton create, update, delete, show;

    sms() {
        c = getContentPane();
        c.setLayout(null);

        create = new JButton("Create");
        update = new JButton("Update");
        delete = new JButton("Delete");
        show = new JButton("Show");

        Font f = new Font("Arial", Font.PLAIN, 20);
        create.setFont(f);
        update.setFont(f);
        delete.setFont(f);
        show.setFont(f);

        create.setBounds(200, 100, 200, 40);
        update.setBounds(200, 200, 200, 40);
        delete.setBounds(200, 300, 200, 40);
        show.setBounds(200, 400, 200, 40);

        c.add(create);
        c.add(update);
        c.add(delete);
        c.add(show);

        create.addActionListener(e -> new Create(this));
        update.addActionListener(e -> new Update(this));
        delete.addActionListener(e -> new Delete(this));
        show.addActionListener(e -> new Show(this));

        setTitle("Main Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class Create extends JFrame {

    Container c;
    JLabel name, rollno, marks1, marks2, marks3;
    JTextField tname, trollno, tmarks1, tmarks2, tmarks3;
    JButton submit, back;

    Create(JFrame parent) {
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

        name.setBounds(100, 10, 400, 40);
        tname.setBounds(100, 50, 400, 40);
        rollno.setBounds(100, 100, 400, 40);
        trollno.setBounds(100, 140, 400, 40);
        marks1.setBounds(100, 190, 400, 40);
        tmarks1.setBounds(100, 230, 400, 40);
        marks2.setBounds(100, 280, 400, 40);
        tmarks2.setBounds(100, 320, 400, 40);
        marks3.setBounds(100, 370, 400, 40);
        tmarks3.setBounds(100, 410, 400, 40);
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

        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        submit.addActionListener(e -> {
            String nameText = tname.getText().trim();
            String rollnoText = trollno.getText().trim();
            String marks1Text = tmarks1.getText().trim();
            String marks2Text = tmarks2.getText().trim();
            String marks3Text = tmarks3.getText().trim();


            if (nameText.equals("") || !nameText.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(c, "Enter a correct name.");
                tname.setText("");
                tname.requestFocus();
                return;
            }


            if(rollnoText.equals("")){
                JOptionPane.showMessageDialog(c, "Enter roll no.");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            if(marks1Text.equals("") || marks2Text.equals("") || marks3Text.equals("")){
                JOptionPane.showMessageDialog(c, "Enter marks");
                tmarks1.setText("");
                tmarks2.setText("");
                tmarks3.setText("");
                tmarks1.requestFocus();
                return;
            }

            int rollno = Integer.parseInt(rollnoText);
            int marks1 = Integer.parseInt(marks1Text);
            int marks2 = Integer.parseInt(marks2Text);
            int marks3 = Integer.parseInt(marks3Text);

            if(rollno < 1){
                JOptionPane.showMessageDialog(c, "Enter crrect roll no. ");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            if(marks1<0 || marks1>100 || marks3<0 || marks2>100 || marks3<0 || marks3>100){
                JOptionPane.showMessageDialog(c, "Enter correct marks ");
                tmarks1.setText("");
                tmarks2.setText("");
                tmarks3.setText("");
                tmarks1.requestFocus();
                return;
            }


            try {

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/sms_20june24";
                Connection con = DriverManager.getConnection(url, "root", "abc123");

                String sql = "insert into students (rollno, name, marks1, marks2, marks3) values(?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, rollno);
                pst.setString(2, nameText);
                pst.setInt(3, marks1);
                pst.setInt(4, marks2);
                pst.setInt(5, marks3);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(c, "Thank you");
                tname.setText("");
                trollno.setText("");
                tmarks1.setText("");
                tmarks2.setText("");
                tmarks3.setText("");
                tname.requestFocus();
                con.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(c, "Please enter valid numbers for roll number and marks.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(c, "Issue: " + ex);
            }
        });

        setTitle("Create Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class Update extends JFrame {

    Container c;
    JLabel rollno, name, marks1, marks2, marks3;
    JTextField trollno, tname, tmarks1, tmarks2, tmarks3;
    JButton submit, back;

    Update(JFrame parent) {
        c = getContentPane();
        c.setLayout(null);

        rollno = new JLabel("Enter Roll no. ");
        trollno = new JTextField(10);
        name = new JLabel("Enter Name ");
        tname = new JTextField(10);
        marks1 = new JLabel("Enter marks of 1st subject");
        tmarks1 = new JTextField(10);
        marks2 = new JLabel("Enter marks of 2nd subject");
        tmarks2 = new JTextField(10);
        marks3 = new JLabel("Enter marks of 3rd subject");
        tmarks3 = new JTextField(10);
        submit = new JButton("Submit");
        back = new JButton("Back");

        Font f = new Font("Arial", Font.PLAIN, 20);
        rollno.setFont(f);
        trollno.setFont(f);
        name.setFont(f);
        tname.setFont(f);
        marks1.setFont(f);
        tmarks1.setFont(f);
        marks2.setFont(f);
        tmarks2.setFont(f);
        marks3.setFont(f);
        tmarks3.setFont(f);
        submit.setFont(f);
        back.setFont(f);

        rollno.setBounds(100, 10, 400, 40);
        trollno.setBounds(100, 50, 400, 40);
        name.setBounds(100, 100, 400, 40);
        tname.setBounds(100, 140, 400, 40);
        marks1.setBounds(100, 190, 400, 40);
        tmarks1.setBounds(100, 230, 400, 40);
        marks2.setBounds(100, 280, 400, 40);
        tmarks2.setBounds(100, 320, 400, 40);
        marks3.setBounds(100, 370, 400, 40);
        tmarks3.setBounds(100, 410, 400, 40);
        submit.setBounds(200, 450, 200, 40);
        back.setBounds(200, 500, 200, 40);

        c.add(rollno);
        c.add(trollno);
        c.add(name);
        c.add(tname);
        c.add(marks1);
        c.add(tmarks1);
        c.add(marks2);
        c.add(tmarks2);
        c.add(marks3);
        c.add(tmarks3);
        c.add(submit);
        c.add(back);

        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        submit.addActionListener(e -> {
            String rollnoText = trollno.getText().trim();
            String nameText = tname.getText().trim();
            String marks1Text = tmarks1.getText().trim();
            String marks2Text = tmarks2.getText().trim();
            String marks3Text = tmarks3.getText().trim();

            

            if (nameText.equals("") || !nameText.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(c, "Enter correct values.");
                return;
            }

            if(rollnoText.equals("")){
                JOptionPane.showMessageDialog(c, "Enter roll no.");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            if(marks1Text.equals("") || marks2Text.equals("") || marks3Text.equals("")){
                JOptionPane.showMessageDialog(c, "Enter marks");
                tmarks1.setText("");
                tmarks2.setText("");
                tmarks3.setText("");
                tmarks1.requestFocus();
                return;
            }

            
            int rollno = Integer.parseInt(rollnoText);
            int marks1 = Integer.parseInt(marks1Text);
            int marks2 = Integer.parseInt(marks2Text);
            int marks3 = Integer.parseInt(marks3Text);


            if(rollno < 1){
                JOptionPane.showMessageDialog(c, "Enter crrect roll no. ");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            if(marks1<0 || marks1>100 || marks3<0 || marks2>100 || marks3<0 || marks3>100){
                JOptionPane.showMessageDialog(c, "Enter correct marks ");
                tmarks1.setText("");
                tmarks2.setText("");
                tmarks3.setText("");
                tmarks1.requestFocus();
                return;
            }

            try {

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/sms_20june24";
                Connection con = DriverManager.getConnection(url, "root", "abc123");

                String sql = "update students set name=?, marks1=?, marks2=?, marks3=? where rollno=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, nameText);
                pst.setInt(2, marks1);
                pst.setInt(3, marks2);
                pst.setInt(4, marks3);
                pst.setInt(5, rollno);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(c, "Record updated successfully");
                con.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(c, "Please enter valid numbers for roll number and marks.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(c, "Issue: " + ex);
            }
        });

        setTitle("Update Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class Delete extends JFrame {

    Container c;
    JLabel rollno;
    JTextField trollno;
    JButton submit, back;

    Delete(JFrame parent) {
        c = getContentPane();
        c.setLayout(null);

        rollno = new JLabel("Enter Roll no. ");
        trollno = new JTextField(10);
        submit = new JButton("Submit");
        back = new JButton("Back");

        Font f = new Font("Arial", Font.PLAIN, 20);
        rollno.setFont(f);
        trollno.setFont(f);
        submit.setFont(f);
        back.setFont(f);

        rollno.setBounds(100, 10, 400, 40);
        trollno.setBounds(100, 50, 400, 40);
        submit.setBounds(200, 100, 200, 40);
        back.setBounds(200, 150, 200, 40);

        c.add(rollno);
        c.add(trollno);
        c.add(submit);
        c.add(back);

        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        submit.addActionListener(e -> {
            String rollnoText = trollno.getText().trim();
            

            if (rollnoText.equals("")) {
                JOptionPane.showMessageDialog(c, "Enter a roll number.");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            int rollno = Integer.parseInt(rollnoText);

            if(rollno<0){
                JOptionPane.showMessageDialog(c, "Enter valid roll no.");
                trollno.setText("");
                trollno.requestFocus();
                return;
            }

            try {


                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/sms_20june24";
                Connection con = DriverManager.getConnection(url, "root", "abc123");

                String sql = "delete from students where rollno=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, rollno);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(c, "Record deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(c, "No record found with the given roll number");
                }
                con.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(c, "Please enter a valid roll number.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(c, "Issue: " + ex);
            }
        });

        setTitle("Delete Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class Show extends JFrame {

    Container c;
    JTextArea tdata;
    JButton back;

    Show(JFrame parent) {
        c = getContentPane();
        c.setLayout(null);

        tdata = new JTextArea();
        tdata.setEditable(false);
        back = new JButton("Back");


        Font f = new Font("Arial", Font.PLAIN, 18);
        tdata.setFont(f);
        back.setFont(f);


        tdata.setBounds(30, 50, 540, 400);
        back.setBounds(250, 470, 100, 40);

        c.add(tdata);
        c.add(back);

        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/sms_20june24";
            Connection con = DriverManager.getConnection(url, "root", "abc123");

            String sql = "select * from students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int rollno = rs.getInt("rollno");
                String name = rs.getString("name");
                int marks1 = rs.getInt("marks1");
                int marks2 = rs.getInt("marks2");
                int marks3 = rs.getInt("marks3");

                tdata.append("Roll no: " + rollno + ", Name: " + name + ", Subject1: " + marks1 + ", Subject2: " + marks2 + ", Subject3: " + marks3 + "\n");
            }
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c, "Issue: " + ex);
        }

        setTitle("Show Page");
        setBackground(Color.RED);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

public class App {
    public static void main(String[] args) {
        new sms();
    }
}

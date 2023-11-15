package student_registration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;

public class RoleofAdmin{

  JFrame f;
  
  RoleofAdmin(){
       f=new JFrame();
       
         JButton b1= new JButton("Registration");
         JButton b2= new JButton("Search");
         JButton b3= new JButton("Update");
         JButton b4= new JButton("Grade Mangement");
         
         b1.setBounds(150,150,150,30);
         b2.setBounds(150,200,150,30);
         b3.setBounds(150,250,150,30);
         b4.setBounds(150,300,150,30);

         f.add(b1);
         f.add(b2);
         f.add(b3);
         f.add(b4 );
         
         f.setSize(500,600);
            f.setLayout(null);
         f.setVisible(true);
    
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          f.setVisible(false);
          new showregOptions();
         }
         
     });
         b3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             //f.setVisible(false);
           //new showsearchengin();
          }
       });
        b4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          f.setVisible(false);
          Userinformation reg=new Userinformation();          
         }
     });
 
 }
  

  public class showregOptions {
     JFrame f;
    showregOptions(){
        f=new JFrame();
        JButton b1= new JButton("Student Register");
        JButton b2= new JButton("Course Register");
        JButton b3= new JButton("Back");
        b1.setBounds(150,150,150,30);
        b2.setBounds(150,200,150,30);
        b3.setBounds(150,300,150,30);
        
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(500,600);
        f.setLayout(null);
        f.setVisible(true);
       b1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                StudentRegistration reg=new StudentRegistration();               
                System.out.println("Register button clicked");
          }
      });
       b2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
           new show_reg_cou1();
          }
      });
        b3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                RoleofAdmin reg=new RoleofAdmin();               
                System.out.println("Register button clicked");
         }
     });
      }
  }
  public class  show_reg_cou1 {
     JFrame f;
     show_reg_cou1(){
        f=new JFrame();
        JLabel l1=new JLabel("Course Name");
        JLabel l2=new JLabel("Course Code");
        JLabel l3=new JLabel("Course Credit");
        JTextField t1=new JTextField();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        JButton b1=new JButton("SAVE");
        JButton back=new JButton("BACK");
       
        l1.setBounds(50,50,100,30);
        t1.setBounds(150,50,150,30);
        l2.setBounds(50,100,100,30);
        t2.setBounds(150,100,150,30);
        l3.setBounds(50,150,100,30);
        t3.setBounds(150,150,150,30);
        b1.setBounds(150,250,150,30);
        back.setBounds(150,280,150,30);
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(b1);
        f.add(back);
        f.setSize(500,600);
        f.setLayout(null);
        f.setVisible(true);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn;
            ResultSet rs;
            PreparedStatement pst; 
            String coursename = t1.getText();
            String coursecode = t2.getText();
            String coursecredit = t3.getText();
              
        String sql = "insert into course(coursename,courseid,credithour) values(?,?,?)";        
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423");
            pst = conn.prepareStatement(sql);
            pst.setString(1, coursename);
            pst.setString(2, coursecode);
            pst.setString(3, coursecredit);
  
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Registered!");
            f.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
          }
        });
        back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                showregOptions reg=new showregOptions();               
                System.out.println("Back button clicked");
         }
     });
  }
  public class showsearchengin {
    JFrame f;
    showsearchengin(){
      f= new JFrame();
      JLabel l1 = new JLabel("Enter User ID");
      JLabel l2 = new JLabel("First Name");
      JLabel l3 = new JLabel("Last Name");
      JLabel l4 = new JLabel("Gender");
      JLabel l5 = new JLabel("Year");
      JTextField t1 = new JTextField();
      JTextField t2 = new JTextField();
      JTextField t3 = new JTextField();
      JTextField t4 = new JTextField();
      JTextField t5 = new JTextField();
      JButton b1 = new JButton("Search");
      JButton b2 = new JButton("update");
      l1.setBounds(100, 100, 100, 30);
      t1.setBounds(200, 100, 150, 30);
      l2.setBounds(30, 200, 100, 30);
      t2.setBounds(150, 200, 150, 30);
      l3.setBounds(30, 240, 100, 30);
      t3.setBounds(150, 240, 150, 30);
      l4.setBounds(30, 280, 100, 30);
      t4.setBounds(150, 280, 150, 30);
      l5.setBounds(30, 320, 100, 30);
      t5.setBounds(150, 320, 150, 30);
      b1.setBounds(370, 100, 100, 30);
      b2.setBounds(200, 380, 100, 30);
      f.add(l1);
      f.add(t1);
      f.add(l2);
      f.add(t2);
      f.add(l3);
      f.add(t3);
      f.add(l4);
      f.add(t4);
      f.add(l5);
      f.add(t5);
      f.add(b1);
      f.add(b2);
      f.setSize(500, 600);
      f.setLayout(null);
      f.setVisible(true);
      
      }
    }
   }
    
    public static void main(String [] arg) {
        new RoleofAdmin();
        
    }
}

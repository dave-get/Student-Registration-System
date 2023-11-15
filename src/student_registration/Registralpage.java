package student_registration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class Registralpage {
    
    private static void showAdminOptions() {
        JFrame f = new JFrame();
        JButton b1 = new JButton("Registration");
        JButton b2 = new JButton("Search");
        JButton b3 = new JButton("Grade Management");
        b1.setBounds(150, 150, 150, 30);
        b2.setBounds(150, 200, 150, 30);
        b3.setBounds(150, 250, 150, 30);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showsearchengin();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showregOptions();
            }
        });
    }
    
    private static void showregOptions() {
      JFrame f=new JFrame();
      JButton b1= new JButton("Student Register");
      JButton b2= new JButton("Course Register");
     
      b1.setBounds(150,150,150,30);
      b2.setBounds(150,200,150,30);
      f.add(b1);
      f.add(b2);
      f.setSize(500,500);
      f.setLayout(null);
      f.setVisible(true);
     b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          show_reg_cou();
        }
    });
    }

    private static void show_reg_cou() {
      JFrame f=new JFrame();
      JLabel l1=new JLabel("Course Name");
      JLabel l2=new JLabel("Course Code");
      JLabel l3=new JLabel("Course Credit");
      JTextField t1=new JTextField();
      JTextField t2=new JTextField();
      JTextField t3=new JTextField();
      JButton b1=new JButton("SAVE");
     JButton b = new JButton("Back");
     b.setBounds(175, 300, 100, 30);
      l1.setBounds(50,50,100,30);
      t1.setBounds(150,50,150,30);
      l2.setBounds(50,100,100,30);
      t2.setBounds(150,100,150,30);
      l3.setBounds(50,150,100,30);
      t3.setBounds(150,150,150,30);
      b1.setBounds(150,250,150,30);
      f.add(l1);
      f.add(t1);
      f.add(l2);
      f.add(t2);
      f.add(l3);
      f.add(t3);
      f.add(b1);
      f.add(b);
      f.setSize(500,500);
      f.setLayout(null);
      f.setVisible(true);
      b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            f.dispose(); // Close the registration frame
            showregOptions(); // Show the previous frame
         }
     });
}
    
   private static void showsearchengin() {
        JFrame f = new JFrame();
        JLabel l1 = new JLabel("Enter User ID");
        JTextField t1 = new JTextField();
        JButton b1 = new JButton("Search");
        JTable table = new JTable(); // Create an empty table
        JScrollPane scrollPane = new JScrollPane(table); // Add table to a scroll pane
        JPanel panel = new JPanel(new BorderLayout()); // Create a panel with BorderLayout
        panel.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of the panel

        l1.setBounds(100, 150, 100, 30);
        t1.setBounds(200, 150, 150, 30);
        b1.setBounds(200, 200, 100, 30);
        panel.setBounds(50, 250, 400, 200); // Set panel bounds
        f.add(l1);
        f.add(t1);
        f.add(b1);
        f.add(panel); // Add the panel to the frame
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

        // Add action listener to the search button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = t1.getText();
                // Assuming you have the necessary data to populate the table based on the user ID
                String[][] tableData = {
                        {"Data 1", "Data 2", "Data 3"},
                        {"Data 4", "Data 5", "Data 6"},
                        {"Data 7", "Data 8", "Data 9"}
                };
                String[] columnNames = {"Column 1", "Column 2", "Column 3"};

                // Update the table with the new data
                table.setModel(new DefaultTableModel(tableData, columnNames));
            }
        });
    }   
    public static void main(String[] args) {
        JFrame f = new JFrame("well come");
        JButton b1 = new JButton("Student");
        JButton b2 = new JButton("Admin");
        b1.setBounds(150, 100, 100, 30);
        b2.setBounds(150, 150, 100, 30);
        f.add(b1);
        f.add(b2);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

        // Add action listener to the "Admin" button
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showAdminOptions();
            }
       });      
  }    

}
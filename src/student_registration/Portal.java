package student_registration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import student_registration.DisplayStudent;
import student_registration.Registralpage;

public class Portal extends JFrame {
    public Portal() {
        super("Login page");

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create the "Student" button
        JButton studentButton = new JButton("STUDENT");
        buttonPanel.add(studentButton);

        // Create the "Register" button
        JButton registerButton = new JButton("REGISTRAL");
        buttonPanel.add(registerButton);

        // Add the button panel to the frame's content pane
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        
        // Add action listener to the "Student" button
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginPage ob = null;
                try {
                    ob = new LoginPage();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                }
                ob.setVisible(true);  
            }
        });

        // Add action listener to the "Register" button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RoleofAdmin reg=new RoleofAdmin();               
                System.out.println("Register button clicked");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Portal();
            }
        });
    }
}
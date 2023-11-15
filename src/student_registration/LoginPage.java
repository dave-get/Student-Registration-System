package student_registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
     //String usernamee = usernameField.getText();
    public LoginPage() throws ClassNotFoundException, SQLException {
        super("Login page");
   
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 5));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        
        
        Dimension fieldSize = new Dimension(usernameField.getPreferredSize().width, 1);
        buttonPanel.add(loginButton);
        
        JButton back=new JButton("BACK");
        buttonPanel.add(back);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        add(mainPanel);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                String sql="select * from Students where studentid=? and Spassword=?";
         try{
             
             conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423");
             
             pst=conn.prepareStatement(sql);
             pst.setString(1, usernameField.getText());
             pst.setString(2, passwordField.getText());
             
             rs=pst.executeQuery();
             if(rs.next()){
                setVisible(false);
                DisplayStudent ob= new DisplayStudent();
                ob. setVisible(true);
                 rs.close();
                 pst.close();
             }
             else {
                    JOptionPane.showMessageDialog(null,"incorrect credental");
                }
             
         }catch (SQLException ex){
             JOptionPane.showMessageDialog(null, e);
         }
         finally{
             try{
                 rs.close();
                 pst.close();
             }catch(SQLException ex){
             }
         }
        }     
        }); 


    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = null;
            try {
                loginPage = new LoginPage();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            loginPage.setVisible(true);
        });
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
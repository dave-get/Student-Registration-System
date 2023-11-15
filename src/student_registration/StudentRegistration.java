package student_registration;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import student_registration.RoleofAdmin.showregOptions;

public class StudentRegistration extends JFrame {
    private final JPanel all;
    private final JLabel Firstlabel, SecondLabel,  Yearlabel, GenderLabel, Passwordlable, Confirm_passwordlable,idlabel;
    private final JTextField First, Last, year, Newpassword, Confirm_password,id;
    private JComboBox<String> Gender;
    private JLabel selected;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst; 
    
    public StudentRegistration() {
        super("Student Registration");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 600);
        setVisible(true);
        
        all = new JPanel();
        all.setLayout(new GridBagLayout());
        all.setBounds(100, 100, 50, 100);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15,15);
        
        JLabel titleLabel = new JLabel("Student Registration");
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(250, 20, 300, 30);

        add(titleLabel);
        idlabel =new JLabel("User Name");
        id = new JTextField(20);
        Firstlabel = new JLabel("First Name:");
        First = new JTextField(20);
        SecondLabel = new JLabel("Second Name:");
        Last = new JTextField(20);
        GenderLabel = new JLabel("Gender");
        Gender = new JComboBox<>(new String[]{"Male", "Female"});
        Yearlabel = new JLabel("AccadamicYear:");
        year = new JTextField(20);
        Passwordlable = new JLabel("New password:");
        Newpassword = new JPasswordField(20);
        Confirm_passwordlable = new JLabel("Confirm Password");
        Confirm_password = new JPasswordField(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        all.add(idlabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        all.add(id, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        all.add(Firstlabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        all.add(First, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        all.add(SecondLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        all.add(Last, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        all.add(GenderLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        all.add(Gender, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        all.add(Yearlabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        all.add(year, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        all.add(Passwordlable, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        all.add(Newpassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        all.add(Confirm_passwordlable, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        all.add(Confirm_password, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 7;
        all.add(registerButton, gbc);
        
        JButton addCourseButton = new JButton("Add Course");
        gbc.gridx = 1;
        gbc.gridy = 7;
        all.add(addCourseButton, gbc);
        
//        JButton BackButton = new JButton("Back");
//        gbc.gridx = 2;
//        gbc.gridy = 7;
//        all.add(BackButton, gbc);

   
        Firstlabel.setHorizontalAlignment(SwingConstants.LEFT);
        SecondLabel.setHorizontalAlignment(SwingConstants.LEFT);
        GenderLabel.setHorizontalAlignment(SwingConstants.LEFT);
        Yearlabel.setHorizontalAlignment(SwingConstants.LEFT);
        Passwordlable.setHorizontalAlignment(SwingConstants.LEFT);
        Confirm_passwordlable.setHorizontalAlignment(SwingConstants.LEFT);
        
        registerButton.setPreferredSize(new Dimension(100, 20));
        Gender.setPreferredSize(new Dimension(185, 20));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Registration");
        titledBorder.setTitleColor(Color.WHITE);

        selected = new JLabel("Selected Item: ");
        all.setBackground(Color.LIGHT_GRAY);
        add(all);

        // Add action listener to the gender combo box
        Gender.addActionListener((ActionEvent e) -> {
            String selectedGender = (String) Gender.getSelectedItem();
            selected.setText(selectedGender);
        });
        // Add action listener to the register button
registerButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = id.getText();
        String firstName = First.getText();
        String lastName = Last.getText();
        String gender = (String) Gender.getSelectedItem();
        String sYear = year.getText();
        String password = Newpassword.getText();
        if (userName.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                gender.isEmpty() || sYear.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(StudentRegistration.this,
                    "Please fill in all the required fields.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Exit the method without registering
        }
        String sql = "insert into Students values(?,?,?,?,?,?)";        
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423");
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, gender);
            pst.setString(5, sYear);
            pst.setString(6, password);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Registered!");
            setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
});
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Course reg=new Course(); 
                reg.setVisible(true);
            }
        });
//        BackButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e){
//                setVisible(false);
//                showregOptions reg=new showregOptions();               
//                System.out.println("Register button clicked");
//          }
//        });
    
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRegistration();
            }
        });
    }
}
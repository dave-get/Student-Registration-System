package student_registration;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import student_registration.connectDB;

public class Userinformation extends JFrame {
    private final JPanel mainPanel;
    private final JPanel inputPanel;
    private final JPanel tablePanel;
    private final JLabel usernameLabel, gradeLabel, coursecodelabel, gpaLabel;
    private final JTextField usernameField, coursecodeField, gradeField;
    private final DefaultTableModel tableModel;
    private final JTable table;
  Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public Userinformation() {
        super("GPA Calculator");
        conn=connectDB.connect();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTH; // Align components to the top

        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Course"));

        usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(usernameField, gbc);

        coursecodelabel = new JLabel("CourseCode:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(coursecodelabel, gbc);
        
        coursecodeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(coursecodeField, gbc);
        
        gradeLabel = new JLabel("Grade:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(gradeLabel, gbc);

        gradeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(gradeField, gbc);

        JButton addButton = new JButton("Add");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(addButton, gbc);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Course List"));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Course");
        tableModel.addColumn("Credit Hour");
        tableModel.addColumn("Grade");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        gpaLabel = new JLabel("GPA: ");
        gpaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tablePanel.add(gpaLabel, BorderLayout.SOUTH);

    addButton.addActionListener(new ActionListener() {
        @Override
        
        public void actionPerformed(ActionEvent e) {
            String userName = usernameField.getText();
            String coursecode = coursecodeField.getText();
            String grade = gradeField.getText();
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            try{     
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423"); 
                String sql="INSERT INTO Grade (studentid, courseid, Grade) VALUES (?, ?, ?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userName);
                pst.setString(2, coursecode);
                pst.setString(3, grade);
                    pst.execute();
                JOptionPane.showMessageDialog(null, "Successfully Registered!");
            
            }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            
            }
            try {
            String Grades = null ;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/yourDatabaseName", "username", "password");
            String sql = "SELECT g.courseid, g.Grade, c.coursename, c.credithour\n" +
"FROM Grade g\n" +
"INNER JOIN Course c ON g.courseid = c.courseid\n" +
"WHERE g.studentid = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,usernameField.getText());
            rs = pst.executeQuery();
            
            int totalGradePoints = 0;
            int totalCreditHours = 0;

            while(rs.next()) {
                
                int creditHour = rs.getInt("credit_hours");
                int gradePoint = 0;
                switch (grade) {
                    case "A":
                       gradePoint = 4;
                       break;
                    case "B":
                       gradePoint = 3;
                       break;
                    case "C":
                       gradePoint = 2;
                       break;
                    case "D":
                       gradePoint = 1;
                       break;
                    case "F":
                       gradePoint = 0;
                       break;
                }
                totalGradePoints += gradePoint * creditHour;
                totalCreditHours += creditHour;
            }
            double gpa = (double) totalGradePoints / totalCreditHours; 
                
                String in="update  GPA set GPA=? where studentid=?";   
                pst =conn.prepareStatement(in);
                pst.setDouble(1,gpa);
                pst.setString(2,usernameField.getText());
                
                rs = pst.executeQuery();
                
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        }
    });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        mainPanel.add(inputPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty =1.0;
        mainPanel.add(tablePanel, gbc);

        add(mainPanel);
    }

    private double calculateGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }
    
    
    
    public static void main(String[] args) {
        
        new Userinformation();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
          
    
    }
}
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

public class Course extends JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst; 
    public Course() {
        super("Course Selection");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Set the background color to gray
        getContentPane().setBackground(Color.GRAY);

        // Create a main panel using BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel for the title label using FlowLayout
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.GRAY);
        
        // Course Title
        JLabel titleLabel = new JLabel("Course Selection");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Add the title panel to the main panel at the top
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        JLabel idlabel=new JLabel("Enter ID: ");
        JTextField idtxet=new JTextField(10);
        idlabel.setBounds(150,50,150,30);
        idtxet.setBounds(220,50,150,25);
        add(idlabel);
        add(idtxet);
        
        // Create a panel for the course list using GridBagLayout
        JPanel coursePanel = new JPanel(new GridBagLayout());
        coursePanel.setBackground(Color.GRAY);

        // Create constraints for GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        // Course List
        String[] courses = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5"};

        for (String course : courses) {

            JLabel courseLabel = new JLabel(course);
            coursePanel.add(courseLabel, constraints);

            JCheckBox checkBox = new JCheckBox();
            constraints.gridx = 1; // Place the checkbox in the second column
            coursePanel.add(checkBox, constraints);

            // Increment the row for the next component
            constraints.gridy++;
            constraints.gridx = 0; // Reset gridx to the first column
        }

        // Add the course panel to the main panel
        mainPanel.add(coursePanel, BorderLayout.CENTER);

        // Create a panel for the submission button using FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.GRAY);

        // Submission Button
        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        // Add the button panel to the main panel at the bottom
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "select all from course(courseid) values(?)";
                String sql1 = "select all from Students(studentid) values(?)";
                String sql2= "insert into Studentcourse(studentid,courseid) values(?,?)";
                try {
                    conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;encrypt=true;trustServerCertificate=true;", "dave", "1423");
                    pst = conn.prepareStatement(sql);

                    Component[] components = coursePanel.getComponents();
                    int courseCount = 0;

                    for (Component component : components) {
                        if (component instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) component;
                            if (checkBox.isSelected()) {
                                JLabel courseLabel = (JLabel) components[courseCount - 1];
                                String courseName = courseLabel.getText();
                                pst.setString(1, courseName);
                                pst.execute();
                            }
                        }
                        courseCount++;
                    }

                    JOptionPane.showMessageDialog(null, "Submitted successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        
    }
        
    public static void main(String[] args) {
        new Course();
    }
}
package student_registration;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class DisplayStudent extends JFrame implements ActionListener{
    JLabel JL_fname,JL_lname,JL_age,JL_id;
    JTextField JT_fname,JT_lname,JT_age,JT_id;
    JButton btn_search,course;
   JTable courseTable;
   DefaultTableModel tableModel;
  
      public DisplayStudent(){
          
          JL_id = new JLabel("Student id:");
          JL_id.setBounds(20, 20, 200, 20);
          JT_id = new JTextField(20);
          JT_id.setBounds(130, 20, 150, 20);
          btn_search = new JButton("Search");
          btn_search.setBounds(300, 20, 80, 20);
          btn_search.addActionListener(this);
          course = new JButton("Course");
          course.setBounds(300, 60, 80, 20);
          course.addActionListener(this);
          setVisible(true);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setSize(500,200);
          
          JL_fname = new JLabel("Full_name:" );
          JL_fname.setBounds(20, 50, 100, 20);
          JT_fname = new JTextField(20);
          JT_fname.setBounds(130, 50, 150, 20);
          JL_lname = new JLabel("AccadamicYear: ");
          JL_lname.setBounds(20, 80, 100, 20);
          JT_lname = new JTextField(20);
          JT_lname.setBounds(130, 80, 150, 20);
          JL_age = new JLabel("GPA: ");
          JL_age.setBounds(20, 110, 100, 20);
          JT_age = new JTextField(20);
          JT_age.setBounds(130, 110, 150, 20);
          setLayout(null);
          add(btn_search);
          add(JL_fname);
          add(JT_fname);
          add(JL_lname);
          add(JT_lname);
          add(JL_age);
          add(JT_age);
          add(JL_id);
          add(JT_id);
          add(course);
          
    // New variables for the table
    
    DefaultTableModel tableModel;
 
    
        // Existing code...

        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourseTable();
                Connection conn = null;
                ResultSet rs = null;
                PreparedStatement pst = null;
                 String id =  JT_id.getText();
                try {
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423");
                
               String query = "SELECT g.courseid, g.Grade, c.coursename " +
                    "FROM Grade g " +
                    "INNER JOIN course c ON g.courseid = c.courseid " +
                    "WHERE g.studentid =?";
                    
                    pst=conn.prepareStatement(query);
                    pst.setString(1,id);
                    rs=pst.executeQuery();
                    courseTable.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }catch(Exception ex){
    
  
}           }
                // Create a new table when the "Course" button is clicked
            
            
        });

        
        course.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
        }// Existing code...
        });
}

    private void createCourseTable() {
        JFrame courseFrame = new JFrame("Course List");
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Grade");

        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        courseFrame.getContentPane().add(scrollPane);

        courseFrame.pack();
        courseFrame.setLocationRelativeTo(null);
        courseFrame.setVisible(true);
    }// Method to create the table for courses
   


    @Override
    public void actionPerformed(ActionEvent e) { 
    Function f = new Function();
    ResultSet rs = null;
    String fn = "Firstname";
    String ln = "AccadamicYear";
    String ge = "GPA";
    
    rs = f.find(JT_id.getText());
    try{
      if(rs.next()){
          String add4=rs.getString("Firstname");
                String add5=rs.getString("Lastname");
                
          JT_fname.setText(add4+" "+add5);
            JT_lname.setText(rs.getString("AccadamicYear"));
              JT_age.setText(rs.getString("GPA"));
              
      }  else{
          JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ID");
      }
    }catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
            }
    }
    
    
   public class Function{
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement ps = null;
       
       public ResultSet find(String s){
           try{
               
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;", "dave", "1423");
           ps = conn.prepareStatement("SELECT Students.*, GPA.GPA FROM Students INNER JOIN"
                   + " GPA ON Students.studentid = GPA.studentid WHERE Students.studentid = ?");
           ps.setString(1,s);
           rs = ps.executeQuery();
           
           }catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
           }
           return rs;
       }
       
   }
   

    
 public static void main(String[] args){
  new DisplayStudent();
 }
 
}
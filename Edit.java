/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahil_lab3;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/**
 *
 * @author placements2017
 */
public class Edit extends Frame implements ActionListener{
    
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/lab2";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   public String sub;
    private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel; 
    public Edit(String user,String subject)
    {
         sub=subject;
        GUI();
    }
     public void GUI()
    {
      mainFrame=new JFrame("Home");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.setBackground(Color.GRAY);
      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
        //System.out.println(sub);
      Edit1();
      
    }
     String t1;
     int t2;
     int t3;
     JTextField id=new JTextField();
     JTextField att=new JTextField("0");
         JTextField mm1=new JTextField("0");
     public void Edit1()
    {
        
       //System.out.println(type);
       headerLabel.setText("HomePage");  
       JPanel panel = new JPanel();
      panel.setBackground(Color.ORANGE);
      panel.setSize(300,300);
      
      GridLayout layout = new GridLayout(0,2);
      layout.setHgap(10);
      layout.setVgap(10);
      
     
      
           JLabel s_id=new JLabel("s_id");
           
           //
          // System.out.println("Hre");
          // System.out.println(t1);
           //id.Set
           JLabel atten=new JLabel("attendance");
           
           System.out.println("here");
           //
           
           JLabel mm=new JLabel("marks");
       
           //
          
         //ResultSet rs=preparedStmtlol.executeQuery();
        JButton done=new JButton("Update");
           
         panel.setLayout(layout);
           panel.add(s_id);
           panel.add(id);
           panel.add(atten);
           panel.add(att);
           panel.add(mm);
           panel.add(mm1);
           panel.add(done);
      controlPanel.add(panel);
      mainFrame.setVisible(true);
     done.addActionListener(this);
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         //To change body of generated methods, choose Tools | Templates.
        //System.out.println(t1);
        t1=id.getText();
        t2=Integer.parseInt(att.getText());
        t3=Integer.parseInt(mm1.getText());
        //System.out.println(t1);
        try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab3","root","root");
          Statement stmt = conn.createStatement();
               System.out.println("Connected to database");
            //    System.out.println(t1); 
           String qury="delete from "+sub+" where s_id = ? ;";
            //System.out.println(qury);
          //int rs1=stmt.executeUpdate(qury); 
          PreparedStatement preparedStmtlol = conn.prepareStatement(qury);
      preparedStmtlol.setString(1, t1);
         preparedStmtlol.execute();
         //  PreparedStatement preparedStmtlol = conn.prepareStatement(qury);
          // preparedStmtlol.setString(1, sub);
      //preparedStmtlol.setString(2, t1);
            //int rs1 = preparedStmtlol.executeUpdate();
        // preparedStmtlol.execute();
         System.out.println("Deleted Successfully");
         //ResultSet rs1=stmt.executeQuery(qury+t1);*/
           PreparedStatement qr=conn.prepareStatement("Insert into "+sub+" values(?,?,?);");
           System.out.println("Query Executed");
           qr.setString(1,t1);
           qr.setInt(2, t2);
           qr.setInt(3, t3);
            int rs = qr.executeUpdate();
            System.out.println("Updated Successfully");
        }catch(Exception e1){
            System.out.println(e1);
        }
    }
}

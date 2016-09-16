/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahil_lab3;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author placements2017
 */
public class Login extends Frame implements ActionListener{
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/lab2";
   public JComboBox dropmenu;
   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   public Login(){
      prepareGUI();
   }

   public static void main(String[] args){
      Login swingLayoutDemo = new Login();  
      swingLayoutDemo.Login();       
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("Login");
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
   }
   JTextField username=new JTextField();
   JPasswordField pass=new JPasswordField();
   String kl;
   public void Login(){
      headerLabel.setText("Instructor/Student Login");      

      JPanel panel = new JPanel();
      panel.setBackground(Color.ORANGE);
      panel.setSize(300,300);
      GridLayout layout = new GridLayout(0,2);
      layout.setHgap(10);
      layout.setVgap(10);
      
      JLabel type=new JLabel("Instructor/Student/Dean");
      String str[]={"Faculty","Student","Dean"};
      dropmenu = new JComboBox(str);
      
     
      JLabel user= new JLabel("UserName");
      
      JLabel password=new JLabel("Password");
      
      JButton log =new JButton("Login");
      JButton close=new JButton("Close");
      log.setBackground(Color.LIGHT_GRAY);
      close.setBackground(Color.LIGHT_GRAY);
      panel.setLayout(layout);        
      panel.add(type); 
      panel.add(dropmenu); 
      panel.add(user); 
      panel.add(username); 
      panel.add(password); 
      panel.add(pass); 
      panel.add(log);
      panel.add(close);
      log.addActionListener(this);
      
      controlPanel.add(panel);
      mainFrame.setVisible(true);  
   }
   private boolean validate_login(String username,String password) {
   try{           
       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab3","root","root");     
       PreparedStatement pst = conn.prepareStatement("Select * from login where id=? and password=? and type=?");
       pst.setString(1, username); 
       pst.setString(2, password);
       //System.out.println(kl);
       pst.setString(3, kl);
       ResultSet rs = pst.executeQuery();                        
       if(rs.next()){
           mainFrame.setVisible(false);
           //System.out.println("Logged In Successfully");
           return true;
       }
       else{
           System.out.println(kl);
           System.out.println("Wrong UserName/Password/Type Format");
           return false; 
       }
   }
   catch(Exception e){
       e.printStackTrace();
       return false;
   } 
   }

     public void actionPerformed(ActionEvent e)
    {
        Login ee=new Login();
    if(username.getText().length()==0)  // Checking for empty field
      System.out.println("Please Enter username");
   else if(pass.getPassword().length==0)  // Checking for empty field
      System.out.println("Please Enter Password");
   else{
       String user1 = username.getText();   // Collecting the input
       char[] pass1 = pass.getPassword(); // Collecting the input
       kl=(String)dropmenu.getSelectedItem();
       String pwd = String.copyValueOf(pass1);  // converting from array to string
       if(validate_login(user1,pwd)){
           
          System.out.println("Logged In Successfully");      
          System.out.println(kl);
          if("Dean".equals(kl)){
            new Home_Dean(user1,kl);  
          }else{
            new Home(user1,kl);
          // kl ==type
          }
       }
       else
         System.out.println("Try Again");
   }
    }
}

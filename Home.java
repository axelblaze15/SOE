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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
//faculty
/**
 *
 * @author placements2017
 */
public class Home extends Frame implements ActionListener{
    private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel; 
   public String type;
   public String kl;
   public String user;
   public String tem;
   public String ko;
   public JComboBox dropmenu;
   public JComboBox dropmenu_sub;
    public Home(String user1,String t)
    {
        
        this.type=t;
        user=user1;
        //System.out.println(type);
        GUI();
    }
    
    public void Homee()
    {
        
       System.out.println(type);
       headerLabel.setText("HomePage");  
       JPanel panel = new JPanel();
      panel.setBackground(Color.ORANGE);
      panel.setSize(300,300);
      
      GridLayout layout = new GridLayout(0,2);
      layout.setHgap(10);
      layout.setVgap(10);
      // str;Select subject from faculty where id=="+user;
      //if("Instructor".equals(type) || "Dean".equals(type)){
      try{
      Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab3","root","root");
          Statement stmt = conn.createStatement();
               System.out.println("Connected to database");
           PreparedStatement qr=conn.prepareStatement("Select subject from connector where id=?");
           System.out.println("Query Executed");
           qr.setString(1,user);
           ResultSet rs = qr.executeQuery();
           String[] subject =new String[5];
           int cnt=0;
            while(rs.next()){
            //    String sub;
                subject[cnt++]=rs.getString("subject");
                //System.out.println(sub);
            }
       JLabel type2=new JLabel("Subjects");
       //String str[]={"View","Edit"};
        dropmenu_sub = new JComboBox(subject);
        
      
         // dropmenu_sub.addActionListener(this);
        JLabel type1=new JLabel("View/Edit");
       String str[]={"View","Edit"};
       dropmenu = new JComboBox(str);
      

      JButton next=new JButton("Proceed");
      JButton close=new JButton("Close");
      
      panel.setLayout(layout);
      panel.add(type2);
      panel.add(dropmenu_sub);
      panel.add(type1);
      panel.add(dropmenu);
      panel.add(next);
      panel.add(close);
      controlPanel.add(panel);
      mainFrame.setVisible(true);
      next.addActionListener(this);
      }catch(Exception e){
          System.out.println(e);
      }
      
       
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
      Homee();
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //String gg=e.getActionCommand();
         //To change body of generated methods, choose Tools | Templates.
        kl=(String)dropmenu.getSelectedItem();
        ko=(String)dropmenu_sub.getSelectedItem();
        if("Student".equals(type) && "Edit".equals(kl)){
            System.out.println("Cannot Edit as a student");
            this.dispose();
        }
        if("View".equals(kl) ){
            System.out.println(ko);
            System.out.println(kl);
            mainFrame.setVisible(false);
            System.out.println("Sending control to View");
            new View(user,ko);
            
        }
        else if("Edit".equals(kl) && ("Dean".equals(type) || "Faculty".equals(type)) )
        {
            mainFrame.setVisible(false);
            new Edit(user,ko);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahil_lab3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author placements2017
 */
public class View {
    
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/lab2";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel; 
   public String sub;
    static JTable table;
    public String user;
    public View(String user1,String Subject)
    {
        user =user1;
        sub=Subject;
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
      View1();
      
    }
    public void View1()
    {
      JPanel panel = new JPanel();
      panel.setBackground(Color.ORANGE);
      panel.setSize(300,300);
      GridLayout layout = new GridLayout(0,3);
      layout.setHgap(10);
      layout.setVgap(10);
      try{
      Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab3","root","root");
          //Statement stmt = conn.createStatement();
               System.out.println("Connected to database");
           Statement qr=conn.createStatement();//prepareStatement("Select * from '"+ sub + "'");
           System.out.println("Query Executed");
         
        String s_id ;

        String atten ;

        String mm ;
            String[] columnNames={"Student_ID","Attendance","Marks"};
          DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnNames);

//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());

//table = new JTable(model);

        table = new JTable();

        table.setModel(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);

        scroll.setHorizontalScrollBarPolicy(

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setVerticalScrollBarPolicy(

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

      //  String from = (String) c1.getSelectedItem();

//String textvalue = textbox.getText();

       

         PreparedStatement pst = conn.prepareStatement("select * from " + sub);

            ResultSet rs1 = pst.executeQuery();
            
             while (rs1.next()) {

                s_id = rs1.getString("s_id");

                atten= rs1.getString("attendance");

                mm = rs1.getString("marks");

                //cou = rs.getString("ucountry");

                model.addRow(new Object[]{s_id, atten, mm});

                //i++;

            }
            
           /*  if("axel".equals(user))
             {
                 System.out.println("herechh");
                 JLabel pp=new JLabel("Subject Taught by:");
                 Statement fg=conn.createStatement();
                 ResultSet df=fg.executeQuery("Select id from connector where subject="+sub);
                 String[] ui = null;
                 int cnt=0;
                 while(df.next()){
                     ui[cnt++]=df.getString("id");
                     ui[cnt++]=",";
                 }
                 //JLabel pp1=new JLabel(ui);
                 int i=0;
                 String lol="";
                 for(i=0;i<cnt;i++){
                     lol.concat(ui[i]);
                 }
                 JLabel pp1=new JLabel(lol);
                 panel.setLayout(layout);
                 panel.add(pp);
                 panel.add(pp1);
                 
             }*/
            panel.add(scroll);
            controlPanel.add(panel);
      mainFrame.setVisible(true);
            //panel.add(table);
      //}String str="select * from ? ";
      }catch(Exception e){
          System.out.println(e);
      }
      
      
        
    }
}

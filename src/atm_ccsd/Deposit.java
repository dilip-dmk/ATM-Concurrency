/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atm_ccsd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Dilip Pc
 */
public class Deposit {
   synchronized void syncdep(String name,int dep1, String val, String an,int at,String pin1) throws IOException
{
    int d,b=0;
    
    java.util.Date time = Calendar.getInstance().getTime();
    //String pin1 = null;
    int p=0;
    BufferedWriter bw = new BufferedWriter(new FileWriter("ReceiptDeposit.TXT"));
    StringBuilder  receipt = new StringBuilder();
    try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CCSD","1234");
                 Statement st = con.createStatement();
                 if (at==1)
                         {
                 ResultSet rs=st.executeQuery("SELECT * from SBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("dep");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from SACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                 }
                 if(p==Integer.parseInt(pin1)){
                 int abal=b+dep1;
                 
                 st.executeQuery("insert into SBAL (accno,email,dep,bal,date1,abal) values('"+an+"','"+val+"','"+dep1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update SBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 receipt.append(time).append("   ").append("Panipat").append("  ").append("Deposit").append("   ").append("Saving A/c : "+an).append("  ").append("Amount : Rs."+dep1).append("   ").append("Ending Balance: Rs."+abal);
                 }
                 else 
                     System.out.println("Wrong pin entered !!! ");
                         }
                 else
                     {
                 ResultSet rs=st.executeQuery("SELECT * from CBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("dep");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from CACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                 }
                 if(p==Integer.parseInt(pin1)){
                 int abal=b+dep1;
                 
                 st.executeQuery("insert into CBAL (accno,email,dep,bal,date1,abal) values('"+an+"','"+val+"','"+dep1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update CBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 receipt.append(time).append("   ").append("Panipat").append("  ").append("Deposit").append("   ").append("Current A/c : "+an).append("  ").append("Amount : Rs."+dep1).append("   ").append("Ending Balance: Rs."+abal);
                 }
                 else 
                     System.out.println("Wrong pin entered !!! ");
                         }
                 bw.write(receipt.toString());
                  bw.flush();
                  bw.newLine();
    }
    catch(ClassNotFoundException | SQLException e){
        System.out.println(e+"error");
    }
    
} 
      public void dep(String name,int dep1, String val, String an,int at,String pin1)
{
    int d,b=0;
    java.util.Date time = Calendar.getInstance().getTime();
   
    int p = 0;
    try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CCSD","1234");
                 Statement st = con.createStatement();
                 if (at==1)
                         {
                 ResultSet rs=st.executeQuery("SELECT * from SBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("dep");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from SACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                 }
                
                 if(p==Integer.parseInt(pin1)){
                     
                 int abal=b+dep1;
                 
                 st.executeQuery("insert into SBAL (accno,email,dep,bal,date1,abal) values('"+an+"','"+val+"','"+dep1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update SBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 }
                 else 
                     System.out.println("Wrong pin entered !!! ");
                         }
                 else
                     {
                 ResultSet rs=st.executeQuery("SELECT * from CBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("dep");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from CACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                 }
                 if(p==Integer.parseInt(pin1)){
                 int abal=b+dep1;
                 
                 st.executeQuery("insert into CBAL (accno,email,dep,bal,date1,abal) values('"+an+"','"+val+"','"+dep1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update CBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 }
                 else 
                     System.out.println("Wrong pin entered !!! ");
                         }
                 
    }
    catch(ClassNotFoundException | SQLException e){
        System.out.println(e);
    }
} 
      public void bal(String val,String an,int at)
      {
          int avb = 0; 
        try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CCSD","1234");
                 Statement st = con.createStatement();
                 if(at==1)
                 {
                 ResultSet rs=st.executeQuery("SELECT * from SBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                  avb=rs.getInt("abal");
                 }
                     System.out.println(avb);
                 
                    String log=val+" balance enquiry Rs. "+avb;
            java.util.Date time = Calendar.getInstance().getTime();   
            st.executeQuery("insert into LOG values('"+log+"','"+ time.toString()+"')");
                 }
                 else
                 {
                    ResultSet rs=st.executeQuery("SELECT * from CBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                  avb=rs.getInt("abal");
                 }
                     System.out.println(avb); 
                    String log=val+" balance enquiry Rs. "+avb;
            java.util.Date time = Calendar.getInstance().getTime();   
            st.executeQuery("insert into LOG values('"+log+"','"+ time.toString()+"')");
                 }
         } catch(ClassNotFoundException | SQLException e)
                         {
                             System.out.println(e);
                         }
      }
}

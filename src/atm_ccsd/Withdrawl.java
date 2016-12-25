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
public class Withdrawl {
    int i=3;
    synchronized void withdraw(String name,int wd1,String val,String an,int at,String pin1) throws IOException
{
    int d,b=0,ab,p = 0;
    BufferedWriter bw = new BufferedWriter(new FileWriter("ReceiptWithdrawl.TXT"));
    StringBuilder  receipt = new StringBuilder();
     java.util.Date time = Calendar.getInstance().getTime();
    try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CCSD","1234");
                 Statement st = con.createStatement();
                 if (at==1)
                         {
                 ResultSet rs=st.executeQuery("SELECT * from SBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("wd");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from SACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                 }
                 if(p==Integer.parseInt(pin1)){
                 int abal=b-wd1;
                 if (abal>=1000){
                st.executeQuery("insert into SBAL (accno,email,wd,bal,date1,abal) values('"+an+"','"+val+"','"+wd1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update SBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 receipt.append(time).append("   ").append("Panipat").append("  ").append("Withdrawl").append("   ").append("Saving A/c : "+an).append("  ").append("Amount : Rs."+wd1).append("   ").append("Ending Balance: Rs."+abal);
                 }
                 else if (abal>=0)
                 {
                 abal=abal-100;    
                 System.out.println("Overdraft limit reached : Rs.100 Penalty");
                 st.executeQuery("insert into SBAL (accno,email,wd,bal,date1,abal) values('"+an+"','"+val+"','"+wd1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update SBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 receipt.append(time).append("   ").append("Panipat").append("  ").append("Withdrawl").append("   ").append("Saving A/c : "+an).append("  ").append("Amount : Rs."+wd1).append("   ").append("Ending Balance: Rs."+abal).append("Fine: Rs.100");
                 }
                 else 
                     System.out.println("Insufficient funds");
                 }
                 else if(i>0){
                     i--;
                     System.out.println("Wrong pin entered !!! " +i+" Attempts left ");
                 }
                 else
                 {
                     String status1="Blocked : Wrong PIN";
            st.executeUpdate("Update SACCT SET status='"+status1+"' where email='"+val+"'");
                     User u=new User();
                     u.setVisible(true);
                    u.setLocationRelativeTo(null);
                     
                 }
                         }
                 else
                     {
                         ResultSet rs=st.executeQuery("SELECT * from CBAL where email='"+val+"' and accno='"+an+"'");
                 while (rs.next()) {
                 d=rs.getInt("wd");
                 b=rs.getInt("abal");  
                 }
                 ResultSet rs1=st.executeQuery("SELECT * from CACCT where email='"+val+"' and accno='"+an+"'");
                 while (rs1.next()) {
                 p=rs1.getInt("pin");
                  }
                 if(p==Integer.parseInt(pin1)){
                 int abal=b-wd1;
                 if (abal>=1000){
                st.executeQuery("insert into CBAL (accno,email,wd,bal,date1,abal) values('"+an+"','"+val+"','"+wd1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update CBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                 receipt.append(time).append("   ").append("Panipat").append("  ").append("Withdrawl").append("   ").append("Current A/c : "+an).append("  ").append("Amount : Rs."+wd1).append("   ").append("Ending Balance: Rs."+abal);
                 }
                 else if (abal>=0)
                 {
                 abal=abal-100;    
                 System.out.println("Overdraft limit reached : Rs.100 penalty");
                 st.executeQuery("insert into CBAL (accno,email,wd,bal,date1,abal) values('"+an+"','"+val+"','"+wd1+"','"+abal+"','"+time.toString()+"','"+abal+"')");
                 st.executeUpdate("Update CBAL SET abal='"+abal+"' where email='"+val+"' and accno='"+an+"'");
                receipt.append(time).append("   ").append("Panipat").append("  ").append("Withdrawl").append("   ").append("Current A/c : "+an).append("  ").append("Amount : Rs."+wd1).append("   ").append("Ending Balance: Rs."+abal).append("Fine :Rs.100");
                 }
                 else 
                     System.out.println("Insufficient funds");
                 }
                 else if(i>0){
                     i--;
                     System.out.println("Wrong pin entered !!! " +i+" Attempts left ");
                 }
                 else
                 {
                     String status1="Blocked : Wrong PIN";
            st.executeUpdate("Update CACCT SET status='"+status1+"' where email='"+val+"'");
                     User u=new User();
                     u.setVisible(true);
                    u.setLocationRelativeTo(null);
                     
                 }
                         }
                  bw.write(receipt.toString());
                  bw.flush();
                  bw.newLine();
    }
    catch(ClassNotFoundException | SQLException e){
        System.out.println(e);
    }
}
}

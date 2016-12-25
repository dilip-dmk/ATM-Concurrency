package atm_ccsd;

import java.awt.HeadlessException;
import java.sql.*;
import java.util.Calendar;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dilip Pc
 */
public class Login {
   synchronized void LoginUser(String a,String u, String p, int at, String an1)
{
    String name="",u1="",p1="",an="",st1="";   
    ResultSet res1 = null,res,res2 = null;
    int i=3;
    System.out.print(a);
        try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CCSD","1234");
                 Statement st = con.createStatement();
                 res = st.executeQuery("SELECT * from CUST_DETAILS where email='"+u+"' and password='"+p+"'");
                 
                 while (res.next()) {
                 u1 = res.getString("email");
                 p1 = res.getString("password");
                 name = res.getString("name");
        }
                 if (at==1)
                 {
                     res1 = st.executeQuery("SELECT * from SACCT where email='"+u+"' and accno='"+an1+"'"); 
                     while (res1.next()) {
                         an=res1.getString("accno");
                         st1=res1.getString("status");
                     }
                 }
                 else if (at==2){
                     res2 = st.executeQuery("SELECT * from CACCT where email='"+u+"' and accno='"+an1+"'");
                      while (res2.next()) {
                         an=res2.getString("accno");
                         st1=res2.getString("status");
                     } 
                 }
                 else 
                     System.out.println( "Please select Account type \n Please enter Account Number");
                     
        if(u.equals(u1) && p.equals(p1) && an1.equals(an)&&st1.equals("ACTIVE")) {
      // JOptionPane.showMessageDialog(null,"Welcome " +name.toUpperCase()+ " !!!");
            String log=u+" logged in";
            java.util.Date time = Calendar.getInstance().getTime();   
            st.executeQuery("insert into LOG values('"+log+"','"+ time.toString()+"')");
        Transaction t=new Transaction(u,an,at);
         t.setVisible(true);
         t.setLocationRelativeTo(null);
        }
        else {
            System.out.println(" Incorrect Account details/Account Status :"+st1);
        i--;
        if (i>0)
                System.out.println(i+" trie(s) left ");        
        else 
        {   String status1="Blocked : Wrong Password";
            st.executeUpdate("Update SACCT SET status='"+status1+"' where email='"+u+"'");
            String log=u+" account blocked "+status1;
            java.util.Date time = Calendar.getInstance().getTime();   
            st.executeQuery("insert into LOG values('"+log+"','"+ time.toString()+"')");
                }
        } }
                catch(ClassNotFoundException | SQLException | HeadlessException e1){
                    System.out.println(e1);
                } 
        System.out.println(a+" logged in !!! ");
}
   
}

   
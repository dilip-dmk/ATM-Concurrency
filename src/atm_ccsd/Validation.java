/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_ccsd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Dilip Pc
 */
public class Validation {
    
    public static boolean validateEmail(final String hex) {
                Pattern pattern= Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}
    
    public static boolean isMobNo(String str) {
        Pattern mobNO = Pattern.compile("\\d{10}");
        Matcher matcher = mobNO.matcher(str);
        if (matcher.find()) 
            return true;
        else 
            return false;
    }
     public static boolean ac(String str) {
        Pattern mobNO = Pattern.compile("\\d{5}");
        Matcher matcher = mobNO.matcher(str);
        if (matcher.find()) 
            return true;
        else 
            return false;
    }
    
       public static boolean isValidDate(String date)
{   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date testDate = null;
    Calendar cal = Calendar.getInstance();
    int year3;
    try
    {
      testDate = sdf.parse(date);
     // System.out.print(testDate);
      cal.setTime(testDate);
      int year = cal.get(Calendar.YEAR);
      Calendar now = Calendar.getInstance();
      int year2 = now.get(Calendar.YEAR); 
      year3=year2-year;
     // System.out.print(year3);
       if (!sdf.format(testDate).equals(date)|| year3<18) 
      return false;
else
    return true;
    }
    catch (ParseException e)
    {
      return false;
    }
}
    public static boolean pswd(String a, String b)
    {
        if (a.equals(b))
            return true;
        else
            return false;                
    }
    public static boolean txt(String a, String b,String c, String d, String e, String f)
    {
        if (!a.isEmpty() || !b.isEmpty() || !c.isEmpty() || !d.isEmpty() || !e.isEmpty() || !f.equals("Select"))
            return false;
        else 
            return true;        
    }
}

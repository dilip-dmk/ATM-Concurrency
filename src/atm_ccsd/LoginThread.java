/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_ccsd;

/**
 *
 * @author Dilip Pc
 */
public class LoginThread extends Thread {
    Login l;
    String un,pswd,an;
    int at;
    public LoginThread(Login l,String a,String un, String pswd,int at, String an)
    {
        super(a);
        this.l=l;
        this.an=an;
        this.at=at;
        this.un=un;
        this.pswd=pswd;
        start();
    }
    @Override
    public void run()
    {
        l.LoginUser(Thread.currentThread().getName(), un, pswd, at, an);
       
    }
}

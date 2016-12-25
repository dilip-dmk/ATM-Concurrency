/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atm_ccsd;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilip Pc
 */
public class WithdrawlThread extends Thread{
    Withdrawl w;
String val,accno,pin;
int wd=0,at;
    public WithdrawlThread(Withdrawl w, String name,int wd, String val,String accno,int at,String pin) {
        super(name);
        this.w=w;
        this.accno=accno;
        this.val=val;
        this.wd=wd;
        this.pin=pin;
        this.at=at;
        start();
        
    }
    @Override
    public void run()
    {
        try {
            w.withdraw(Thread.currentThread().getName(),wd,val,accno,at,pin);
        } catch (IOException ex) {
            Logger.getLogger(WithdrawlThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

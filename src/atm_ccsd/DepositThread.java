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
public class DepositThread extends Thread{
Deposit d;
String val,accno,pin;
int dep=0,at;
    public DepositThread(Deposit d, String name,int dep, String val,String accno,int at,String pin) {
        super(name);
        this.d=d;
        this.accno=accno;
        this.val=val;
        this.dep=dep;
        this.pin=pin;
        this.at=at;
        start();
        
    }
    @Override
    public void run()
    {
    try {
        d.syncdep(Thread.currentThread().getName(),dep,val,accno,at,pin);
    } catch (IOException ex) {
        Logger.getLogger(DepositThread.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}

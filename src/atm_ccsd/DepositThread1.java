/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atm_ccsd;

/**
 *
 * @author Dilip Pc
 */
public class DepositThread1 extends Thread {
    Deposit d;
String val,accno,pin;
int dep=0,at;
    public DepositThread1(Deposit d, String name,int dep, String val,String accno,int at,String pin) {
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
        d.dep(Thread.currentThread().getName(),dep,val,accno,at,pin);
    }
}

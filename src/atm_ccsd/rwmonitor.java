/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atm_ccsd;
class lock{}
/**
 *
 * @author Dilip Pc
 */
public class rwmonitor extends Thread{
    public static lock l=new lock();
 public static boolean writing=false;
 public int thread_id;
 public static int data=0;
 public static int reader_num=0;
 String type,val,an,pin;
 int at,dep;
 Deposit d=new Deposit();
 
 rwmonitor(int i,String s,int dep,String val,String an,int at,String pin)
 {
  thread_id=i;
  type=s;
  this.an=an;
  this.at=at;
  this.dep=dep;
  this.pin=pin;
  this.val=val;
 }
 rwmonitor(int i,String s,String val,String an,int at)
 {
    thread_id=i;
  type=s;
  this.val=val;
  this.an=an;
   this.at=at;
  
 }
    @Override
 public void run()
 {
  if(type.equals("balance"))
  {
   keepreading();
  }
  else
  {
   keepwriting();
  }
 }
 public void keepreading()
 {
   acquirereadlock();
   System.out.println(type+" "+thread_id+" reads "+data);
d.bal(val, an, at);
   releasedreadlock();
 
 }
 public void keepwriting()
 {
 
   acquirewritelock();
   data++;
   System.out.println(type+" "+thread_id+" writes "+data);
   
      d.dep(type, dep, val, an, at, pin);
   releasedwritelock();
 
 }
 public void acquirereadlock()
 {
 
   synchronized(l)
   {
   while(writing)
   {try{
    System.out.println(type+" "+thread_id+"waiting");
    l.wait();
   }catch(InterruptedException e){}}
   reader_num++;
   }
 
 
 }
 public void acquirewritelock()
 {

   synchronized(l)
   {
   while(writing||reader_num>0)
   {try{
    System.out.println(type+" "+thread_id+"waiting");
    l.wait();
   }catch(InterruptedException e){}}
   writing=true;
   }
 
 }
 public void releasedreadlock()
 {
   synchronized(l)
   {
   --reader_num;
   System.out.println("balance"+thread_id+" complete");
   if(reader_num==0)
   {
    l.notifyAll();
   }
   }
 
 }
 public void releasedwritelock()
 {
   synchronized(l)
   {
   writing=false;
   System.out.println("deposit"+thread_id+" complete");
   l.notifyAll();
   }
 
 }
}


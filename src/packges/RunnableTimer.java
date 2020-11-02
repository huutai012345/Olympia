/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packges;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHT
 */
public class RunnableTimer implements Runnable {

    private Thread t;
    private String threadName=null;
    private int time;
    private DataOutputStream dataOut=null ;

    public RunnableTimer(String name, int time,DataOutputStream dataOut) {
        this.threadName = name;
        this.time = time;
        this.dataOut=dataOut;
    }

    @Override
    public void run() {
        try {
            while (this.time > 0) {
                this.time--;
                Thread.sleep(1000);
            }
            
            System.out.println("Hết Thời Gian");
            System.out.println("Bấm Enter Để Xem Điểm");
            this.dataOut.writeUTF("EXIT");
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        } catch (IOException ex) {
            Logger.getLogger(RunnableTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    public void start() {
        System.out.println(threadName+" Bắt Đầu Thi");
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void stop() {
//        System.out.println(threadName + " Hết Thời Gian");
//        t.stop();
//        t.destroy();
    }
}

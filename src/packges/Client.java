/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packges;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author NHT
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 1234);
        DataInputStream dataIn = new DataInputStream(client.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
        Scanner sc = new Scanner(System.in);

        String user, pass, answer;

        for (int j = 0; j < 5; j++) {
            System.out.println("Sinh VIen Thu "+(j+1));
            while (true) {
                System.out.print("Enter UserName: ");
                user = sc.nextLine();
                System.out.print("Enter Password: ");
                pass = sc.nextLine();

                dataOut.writeUTF(user);
                dataOut.writeUTF(pass);
                if ("Sucess".equals(dataIn.readUTF())) {
                    System.out.println("Sucess");
                    break;
                }
                System.out.println("Fail");

            }
           
            RunnableTimer t = new RunnableTimer(user, 10,dataOut);
            t.start();
            
            System.out.println("List Question");
            for (int i = 0; i < 10; i++) {
                String str = dataIn.readUTF();
                if ("END".equals(str)) {
                    System.out.println("End Client");
                    break;
                }

                System.out.println((i+1)+". "+str);

                System.out.println("A. " + dataIn.readUTF());
                System.out.println("B. " + dataIn.readUTF());
                System.out.println("C. " + dataIn.readUTF());
                System.out.println("D. " + dataIn.readUTF());

                System.out.print("Answer " + (i + 1) + " :");
                answer = sc.nextLine();
                dataOut.writeUTF(answer);
            }
            
            t.stop();
            System.out.println("\nPoint: " + dataIn.readInt());
              System.out.println();

        }
    }
}

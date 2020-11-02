/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packges;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author NHT
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ProcessSQL process = new ProcessSQL();

        ServerSocket server = new ServerSocket(1234);

        System.out.println("Server da san sang!!!");
        Socket client = server.accept();
        DataInputStream dataIn = new DataInputStream(client.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());

        Scanner sc = new Scanner(System.in);
        String user, pass, answer, id;

        for (int i = 0; i < 5; i++) {
           
            while (true) {
                user = dataIn.readUTF();
                pass = dataIn.readUTF();
                id = process.checkUser(user, pass);
                if (!"".equals(id)) {
                    dataOut.writeUTF("Sucess");
                    break;
                }
                dataOut.writeUTF("Fail");
            }

            int point = 0;
            List<Question> questionsList = process.layCauHoi();

            for (Question question : questionsList) {
                dataOut.writeUTF(question.getContent());

                dataOut.writeUTF(question.getA());
                dataOut.writeUTF(question.getB());
                dataOut.writeUTF(question.getC());
                dataOut.writeUTF(question.getD());

                answer = dataIn.readUTF();
                if("EXIT".equals(answer))
                {
                    dataOut.writeUTF("END");
                    break;
                }
                if (answer.equals(question.getAnswer())) {
                    point++;
                }

            }

            process.insertBangDiem(id, point);
            dataOut.writeInt(point);
        }

    }
}           

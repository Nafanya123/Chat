package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;

    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args){

        boolean start = true;
        try {
            clientSocket = new Socket("localhost", 4004);
            try {
                while (start)
                {

                    reader = new BufferedReader(new InputStreamReader(System.in));

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                    new ReadMsg(in).start();
                    new WriteMsg(out, reader).start();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                    in.close();
                    out.close();
                }

            } catch (IOException e){
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println("Сервер выключен");
        }
    }
}

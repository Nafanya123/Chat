package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server
{
    public static LinkedList<ServerThread> serverList = new  LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4004);
        System.out.println("Server Started");
        try {
            while (true) {

                Socket socket = server.accept();
                try
                {
                    serverList.add(new ServerThread(socket, serverList));
                }catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}

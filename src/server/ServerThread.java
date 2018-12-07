package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerThread extends Thread
{
    private Socket clientSocket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedWriter out;

    public LinkedList<ServerThread> serverList;

    public ServerThread(Socket clientSocket, LinkedList<ServerThread> serverList) throws IOException {
        this.clientSocket = clientSocket;
        this.serverList = serverList;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
    }

    public void run()
    {
        String word;
        try {
            while (true)
                {
                    word = in.readLine();
                    System.out.println("Echoing: " + word);
                    if(word == null)
                    {
                        break;
                    }
                    else
                    {
                        synchronized (word)
                        {
                            for(ServerThread st: serverList)
                            {
                                System.out.println(st.getName());
                                st.send(word);
                            }
                        }
                    }

                }
        } catch (IOException e) {
            System.out.println("test2");
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}

    }
}

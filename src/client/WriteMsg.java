package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriteMsg extends Thread{

    private BufferedWriter out;
    private BufferedReader reader;

    WriteMsg(BufferedWriter out, BufferedReader reader)
    {
        this.out = out;
        this.reader = reader;
    }

    @Override
    public void run() {

        while (true)
        {
            String word;
            try {

                word = reader.readLine();
                out.write(word + "\n");
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

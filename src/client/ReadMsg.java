package client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by tt506619 on 07.12.18.
 */
public class ReadMsg extends Thread{
    private BufferedReader in;

    ReadMsg(BufferedReader in)
    {
        this.in = in;
    }

    @Override
    public void run() {
        String word;

            try {
                while (true)
                {
                    word = in.readLine();
                    System.out.println(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionWorker implements Runnable {

    private Socket clientSocket = null;

    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    public ConnectionWorker(Socket socket)
    {
        clientSocket = socket;
    }

    @Override
    public void run() {

        send();

        try{
            inputStream = clientSocket.getInputStream();

        }   catch (Exception e) {
            System.out.println("CANT GET INPUT STREAM ");
        }

        byte[] buffer = new byte[1024 * 4];

        //asdassadsa
        while(true) {
            try{
                int count = inputStream.read(buffer, 0, buffer.length);

                if(count > 0) {
                    System.out.println(new String(buffer,0,count));
                } else
                    if (count == -1) {
                        System.out.println("close socket");
                        clientSocket.close();
                        break;
                    }
            }   catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

    public void send()
    {
        try {
            Socket andrSocket = new Socket("192.168.232.2", 6789);
            try {
                outputStream = andrSocket.getOutputStream();
            } catch (IOException e) {
                System.out.println("CANT GET OUTPUT STREAM");
            }

            System.out.println(clientSocket.getInetAddress());
            PrintWriter pw = new PrintWriter(outputStream, true);
            pw.write(123123213);
        } catch (IOException e) {
            System.out.println("ANDROID IS MISSING");
        }

    }

}

import java.io.*;
import java.net.*;

class JavaServer {
    public static void main(String args[]) throws Exception {
        String fromclient;

        ServerSocket Server = new ServerSocket (2321);

        System.out.println ("TCPServer Waiting for client on port 2321");

        while(true)
        {
            Socket connected = Server.accept();
            System.out.println( " THE CLIENT"+" "+ connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader (connected.getInputStream()));

            while ( true )
            {
                fromclient = inFromClient.readLine();

                if ( fromclient.equals("q") || fromclient.equals("Q") )
                {
                    connected.close();
                    break;
                }
                else
                {
                    System.out.println( "RECEIVED:" + fromclient );
                }
            }
        }
    }
}
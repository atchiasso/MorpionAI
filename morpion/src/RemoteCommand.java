import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class RemoteCommand {

    private ServerSocket server;
    private int port = 2321;
    private Socket client = null;
    private String fromClient = null;

    public void initializeServer() {
        try {
            server = new ServerSocket(port);
            System.out.println ("Serveur en attente du client sur le port 2321");

            client = server.accept();
            while(client == null) {
                client = server.accept();
                System.out.println( "Le client n'est pas connecté");
            }
            System.out.println( "Le client"+" "+ client.getInetAddress() +":"+client.getPort()+" est connecté");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean sentPlateauToAI(PlateauDeJeu plateau) throws IOException {
        try {
            OutputStream outstream = client.getOutputStream();
            PrintWriter out = new PrintWriter(outstream);
            out.print(plateau.toString());
            out.flush();
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String receiptDataFromAI() throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String receivedData = "";
        while(fromClient == null) {
            fromClient = inFromClient.readLine();
        }

        receivedData = fromClient.substring(0,3);
        fromClient = null;
        System.out.println(receivedData);
        return receivedData;
    }
}
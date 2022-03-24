import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteCommand {

    private ServerSocket server;
    private int port = 2321;
    private Socket client = null;
    private String fromClient = null;

    public void initializeServer() {
        try {
            server = new ServerSocket(port);
            System.out.println ("Serveur en attente du client sur le port 2321");

            while(client == null) {
                Socket client = server.accept();
                System.out.println( "Le client"+" "+ client.getInetAddress() +":"+client.getPort()+"est connecté");
            }
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
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
        String receivedData = "";
        while(fromClient == null) {
            fromClient = inFromClient.readLine();
        }
        receivedData = fromClient;
        fromClient = null;
        System.out.println(receivedData);
        return receivedData;
    }
}
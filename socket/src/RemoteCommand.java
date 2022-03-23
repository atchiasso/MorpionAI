import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class RemoteCommand {

    private ServerSocket middleman;
    private int port = 2321;
    private Socket client;


    protected void createSocketServer()
    {
        try
        {
            while (true){
                middleman = new ServerSocket(port);
                System.out.println("serveur en écoute");
                client = middleman.accept();
                System.out.println("Connexion acceptée");
                middleman.close();
                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                byte[] bytercv  = client.getInputStream().readNBytes(3);
                System.out.println(Arrays.toString(bytercv));
                bytercv[1] = 0x00;
                int[] bytetoint = { 0,0,0};
                int i = 0;
                for (byte bite: bytercv) {
                    bytetoint[i]  = ((bite/16) * 10) + (bite%16);
                    i++;
                }
                System.out.println(Arrays.toString(bytetoint));
                //out.println(Arrays.toString(bytetoint));
                while((line = in.readLine()) != null)
                {
                    System.out.println("echo: " + line);
                    out.println("test");
                }
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
// Read value from AI
    // Convert value read in INT
    // Convert to str
    // return str
    public static void main(String[] args){
        RemoteCommand test2 = new RemoteCommand();
        test2.createSocketServer();
    }

}
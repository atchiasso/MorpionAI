import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class envoi {


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",2321);
        OutputStream test = socket.getOutputStream();
        test.write(new byte[] { 0x30, 0x31, 0x32});
        InputStream read = socket.getInputStream();
        System.out.println(read.readAllBytes().toString());
    }
}
package livestream.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import livestream.models.User;

/**
 * Created by nttungg on 11/13/18.
 */

public class SocketServerThread extends Thread {
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void run(){

        try{
            Socket socket = new Socket("172.16.0.216",9999);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(new User(123, "123", "123", "123", "123"));

        } catch(Exception e) {
        }
    }

}

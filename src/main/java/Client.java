import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread{
    int port;
    Socket connection;
    ObjectInputStream in;
    ObjectOutputStream out;
    private Consumer<Serializable> callback;

    public Client(Consumer<Serializable> callback, int port){
        this.callback = callback;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            connection = new Socket("127.0.0.1", port);
            in = new ObjectInputStream(connection.getInputStream());
            out = new ObjectOutputStream(connection.getOutputStream());
            connection.setTcpNoDelay(true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            GameState state = (GameState) in.readObject();
            callback.accept(state); //ToDo: Define accept to update ClientGUI
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sendGuess(Character selection){
        try{
            out.writeObject(selection);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

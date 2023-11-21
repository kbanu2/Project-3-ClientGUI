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
    CategoriesSceneController sceneController;
    GuessingRoundController guessingRoundController;
    public Client(int port){
        this.port = port;
    }

    public void setSceneController(CategoriesSceneController sceneController){
        this.sceneController = sceneController;
    }

    public void setGuessingRoundController(GuessingRoundController guessingRoundController){
        this.guessingRoundController = guessingRoundController;
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
            //guessingRoundController.accept(state);
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

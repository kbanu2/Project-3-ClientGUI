import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread{
    int port;
    Socket connection;
    ObjectInputStream in;
    ObjectOutputStream out;
    CategoriesSceneController categoriesSceneController;
    GuessingRoundController guessingRoundSceneController;

    public Client(int port){
        this.port = port;
    }

    public void setCategoriesController(CategoriesSceneController categoriesSceneController){
        this.categoriesSceneController = categoriesSceneController;
    }

    public void setGuessingRoundSceneController(GuessingRoundController guessingRoundSceneController){
        this.guessingRoundSceneController = guessingRoundSceneController;
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
            guessingRoundSceneController.accept(state);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
    
    public void pickCategory(int category){
        try{
            out.writeObject(category);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sendGuess(String guess){
        try{
            out.writeObject(guess);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    
}

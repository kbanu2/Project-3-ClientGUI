import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread{
    int port;
    String username;
    Socket connection;
    ObjectInputStream in;
    ObjectOutputStream out;
    CategoriesSceneController categoriesSceneController;
    GuessingRoundController guessingRoundSceneController;
    GameState state;

    public Client(int port, String username){
        this.port = port;
        this.username = username;
    }

    public void setCategoriesController(CategoriesSceneController categoriesSceneController){
        
        this.categoriesSceneController = categoriesSceneController;
    }

    public void setGuessingRoundSceneController(GuessingRoundController guessingRoundSceneController){
        
        this.guessingRoundSceneController = guessingRoundSceneController;
    }

    public void run(){
        try{
            connection = new Socket("127.0.0.1", port);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            connection.setTcpNoDelay(true);

            out.writeObject(username);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            while (true){
                //Keep the client thread running so that we can use the in, out variables
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void pickCategory(int category){
        try {
            out.writeObject(category);
            state = (GameState) in.readObject();
            categoriesSceneController.accept(state);
            guessingRoundSceneController.getGame(state);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getGameState(){
        try{
            state = (GameState) in.readObject();
            categoriesSceneController.accept(state);
            guessingRoundSceneController.getGame(state);
        }
        catch (Exception e){
            e.printStackTrace();
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

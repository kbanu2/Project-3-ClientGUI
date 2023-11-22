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
            int i = 0;

            while (true){
                state = (GameState) in.readObject();
                System.out.println("receiving GUESSES: " +state.guesses_left);
                System.out.println("receiving LENGTH: " +state.length);
                System.out.println("receiving WON/LOST: " +state.roundWon);

                categoriesSceneController.accept(state);
                //System.out.println(in.readObject().toString());
                guessingRoundSceneController.getGame(state);
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
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

//This class controls the events of the Categories Scene of the program and goes to the next scene
//It transfer the data of the chosen category to the Guessing Round scene


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class CategoriesSceneController{
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button Category1, Category2, Category3;

    String username;
    Client client;
    public void setUser(String name){
        username = name;
        
    }

    public void getClient(Client c){
        client = c;
    }

    GuessingGame game;  //Instance of the game logic

    public void InitializeGame(){   //Only called when it's the first game or when the player wants to continue playing after winning/losing
        //clie //FIXME

    }

    public void setGame(GuessingGame g){   //Called after every non-final round
        game = g; 
        
        //If a round has been won check to see which category cannot be played anymore
        if(game.category_track.get(0)==0){
            Category1.setDisable(true);
        }
        
        if(game.category_track.get(1)==0){
            Category2.setDisable(true);
        }

        if(game.category_track.get(2)==0){
            Category3.setDisable(true);
        }
    }

    //Code below handles functionality of each category button

    public void ChooseCategory1(ActionEvent event) throws IOException {
       
        game.pick_from_category(1);
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Food");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.round.guesses));
        grc.getGame(game);
        grc.DisplayWordLabels(game.round.word.length());
        
    }

    public void ChooseCategory2(ActionEvent event) throws IOException {

        game.pick_from_category(2);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);

        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Animals");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.round.guesses));
        grc.getGame(game);
        grc.DisplayWordLabels(game.round.word.length());
    }

    public void ChooseCategory3(ActionEvent event) throws IOException {

        game.pick_from_category(3);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);

        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Countries");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.round.guesses));
        grc.getGame(game);
        grc.DisplayWordLabels(game.round.word.length());
    }

}

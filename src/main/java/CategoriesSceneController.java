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
    GameState game;
    public void setUser(String name){
        username = name;
        
    }

    public void getClient(Client c){
        client = c;
    }


    public void setGame(){   //Called after every non-final round
        
        //If a round has been won check to see which category cannot be played anymore
        if(game.category1==1){
            Category1.setDisable(true);
        }
        
        if(game.category2==1){
            Category2.setDisable(true);
        }

        if(game.category3==1){
            Category3.setDisable(true);
        }
    }

    //Code below handles functionality of each category button

    public void ChooseCategory1(ActionEvent event) throws IOException {
       
        client.pickCategory(1);
        

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
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        grc.getGame(game);
        grc.DisplayWordLabels(game.length);
        
    }

    public void ChooseCategory2(ActionEvent event) throws IOException {

        client.pickCategory(2);
        
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
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        grc.getGame(game);
        grc.DisplayWordLabels(game.length);
    }

    public void ChooseCategory3(ActionEvent event) throws IOException {

        client.pickCategory(3);
    

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
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        grc.getGame(game);
        grc.DisplayWordLabels(game.length);
    }

}

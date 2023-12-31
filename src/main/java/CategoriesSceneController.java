//This class controls the events of the Categories Scene of the program and goes to the next scene
//It transfer the data of the chosen category to the Guessing Round scene


import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CategoriesSceneController{

    public MenuItem tutorialMenuItem;
    public MenuItem exit;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button Category1, Category2, Category3;

    String username;
    static Client client;
    static GameState game;
    public void setUser(String name){
        username = name;
        
    }

    public void accept(GameState g){
        game = g;

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

    public void showTutorial(){
        Popup popup = new Popup();
        Label label1 = new Label("In each category you must correct the correct letters of the word");
        Label label2 = new Label("You get 6 guesses per word, if you guess right, your guesses will not go down");
        Label label3 = new Label("You get 3 attempts to guess the word per category, if you fail then you lose!");
        Label label4 = new Label("If you guess the word in all of the categories, you win!");
        VBox vBox = new VBox(label1,label2,label3,label4);
        popup.getContent().add(vBox);

        popup.show(Category1.getScene().getWindow());

        PauseTransition pause = new PauseTransition(Duration.seconds(10));

        //Delay before changing the scene
        pause.setOnFinished(e -> {
            popup.hide();
        });

        pause.play();
    }

    public void exit(){
        System.exit(0);
    }

    //Code below handles functionality of each category button

    public void ChooseCategory1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);
        client.pickCategory(1);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Food");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        //grc.getGame(game);
        grc.DisplayWordLabels(game.length);
        grc.getClient(client);
        
    }

    public void ChooseCategory2(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);
        client.pickCategory(2);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Animals");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        //grc.getGame(game);
        grc.DisplayWordLabels(game.length);
        grc.getClient(client);
        
        
    }

    public void ChooseCategory3(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuessingRound.fxml"));
        root = loader.load();
        GuessingRoundController grc = loader.getController();
        client.setGuessingRoundSceneController(grc);
        client.pickCategory(3);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        grc.DisplayUser(username);
        grc.DisplayCategory("Countries");
        grc.DisplayWords(String.valueOf(game.words_guessed));
        grc.UpdateGuesses(String.valueOf(game.guesses_left));
        //grc.getGame(game);
        grc.DisplayWordLabels(game.length);
        grc.getClient(client);

    }

}

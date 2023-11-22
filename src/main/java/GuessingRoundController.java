//This class controls the game logic of every round

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GuessingRoundController{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label User, GuessesRemaining, Category, WordsGuessed;
    Label WinLose, Outcome;
    @FXML
    HBox WordPlace, LeftButton, RightButton, TopLabel, BottomLabel;
    @FXML
    Button A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
    Button Exit, PlayAgain;
    ArrayList<Label> letterLabel = new ArrayList<>();

    //GuessingGame game;
    String username;

    Client client;

    public void getClient(Client c){
        client = c;
    }

    GameState game;

    public void getGame(GameState g){
        game = g;
       
    }

    public void accept(GameState g){
        game = g;
    }

    //Code Below Organizes the labels to be displayed


    public void DisplayUser(String name){
        username = name;
        User.setText("User: " + name);
    }

    public void DisplayCategory(String cat) {

        Category.setText("Category: " + cat);
    }

    public void DisplayWords(String words) {

        WordsGuessed.setText("Words Guessed: " + words);
    }

    public void UpdateGuesses(String guess) {

        GuessesRemaining.setText("Guesses Remaining: " + guess);
    }

    public void DisplayWordLabels(int wordsize) {
        
        WordPlace.setSpacing(20);

        for (int i = 0; i < wordsize; i++) {
            letterLabel.add(new Label());
            letterLabel.get(i).setText("_"); 
            letterLabel.get(i).setStyle("-fx-font-size: 3em; -fx-font-family: 'Bell MT';");           
            WordPlace.getChildren().add(letterLabel.get(i));
        }

        WordPlace.setAlignment(Pos.CENTER);
    }

    //Code below deals with the user interaction and outcomes    
    
    //Helper funtion that handles selected button
    public void SelectLetter(Button b, String s, ActionEvent event){
        client.sendGuess(s);
        client.getGameState();

        System.out.println("GUESS:" + s + " OUTCOME: " + game.round_outcome);

        b.setStyle("-fx-background-color:#000000; -fx-text-fill: #FFFFFF;");
        b.setDisable(true);
        
        //When a guess has been made
        //if(game.round_outcome==1){   //Correct guess
            System.out.println("VALID GUESS ");
            //Check if it is a hit or a miss and update label
            for(int i=0; i<game.length; i++){
                letterLabel.get(i).setText(game.word.get(i));
            }

            UpdateGuesses(String.valueOf(game.guesses_left));  //Update remaining guesses

        if(game.round_outcome==-1){    //Wasted all the guesses 
        
            //UpdateGuesses(String.valueOf(game.guesses_left));

            //Checking which category has been played and if the game is over or if the player has additional attempts
            //Checking if category 1 was the currently played category
            if(game.gameWon==-1){
                Outcome = new Label("Better Luck Next Time");
                Outcome.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT'; -fx-text-fill: #FF0000;"); 
                BottomLabel.getChildren().add(Outcome); 
                BottomLabel.setAlignment(Pos.CENTER);

                WinLose = new Label("YOU LOSE");
                WinLose.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT'; -fx-text-fill: #FF0000;"); 
                TopLabel.getChildren().add(WinLose);
                TopLabel.setAlignment(Pos.CENTER);

                PlayAgain = new Button(" Play \nAgain");
                PlayAgain.setPrefWidth(60);
		        PlayAgain.setPrefHeight(45);
		        PlayAgain.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';");
                PlayAgain.setOnMouseEntered(h -> PlayAgain.setStyle("-fx-background-color:#7FFF00; -fx-font-size: 1em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bell MT';"));
		        PlayAgain.setOnMouseExited(h -> PlayAgain.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';")); 
                LeftButton.getChildren().add(PlayAgain);
                LeftButton.setAlignment(Pos.CENTER_LEFT);

                Exit = new Button("Exit");
                Exit.setPrefWidth(60);
		        Exit.setPrefHeight(45);
                Exit.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';");
                Exit.setOnMouseEntered(h -> Exit.setStyle("-fx-background-color:#FF0000; -fx-font-size: 1em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bell MT';"));
		        Exit.setOnMouseExited(h -> Exit.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';")); 
                RightButton.getChildren().add(Exit);
                RightButton.setAlignment(Pos.CENTER_RIGHT);

                PlayAgain.setOnAction(new EventHandler<ActionEvent>() {
			        public void handle(ActionEvent e){
				    FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesScene.fxml"));
                    try{
                        root = loader.load();
                    }catch(Exception ex){
                            
                    }
                    CategoriesSceneController csc = loader.getController();
                        
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show(); 
                    csc.setUser(username);
			        }
		        });

                Exit.setOnAction(e -> System.exit(0));


            }else if(game.roundWon==-1){  //If player has more attempts left to play the game
                Outcome = new Label("Too Bad, Try again");
                Outcome.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT';"); 
                BottomLabel.getChildren().add(Outcome); 
                BottomLabel.setAlignment(Pos.CENTER);

                PauseTransition pause = new PauseTransition(Duration.seconds(2));

                //Delay before changing the scene
		        pause.setOnFinished(e -> {
                    
                    //Go to categories
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesScene.fxml"));
                    try{
                        root = loader.load();
                    }catch(Exception ex){
                            
                    }
                    CategoriesSceneController csc = loader.getController();
                        
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show(); 
                    //csc.setGame(game);
                    csc.setUser(username);
                });

                pause.play();
            }

            
        //If player won the round
        }else if(game.round_outcome==10){  
            for(int i=0; i<game.length; i++){
                letterLabel.get(i).setText(game.word.get(i));
            }

            //Checking if user won the game
            if(game.gameWon==1){
                DisplayWords(String.valueOf(game.words_guessed));

                Outcome = new Label("CONGRATULATIONS!!!");
                Outcome.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT'; -fx-text-fill: #FFD700;"); 
                BottomLabel.getChildren().add(Outcome); 
                BottomLabel.setAlignment(Pos.CENTER);

                WinLose = new Label("YOU WIN!!!");
                WinLose.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT'; -fx-text-fill: #FFD700;"); 
                TopLabel.getChildren().add(WinLose);
                TopLabel.setAlignment(Pos.CENTER);

                PlayAgain = new Button(" Play \nAgain");
                PlayAgain.setPrefWidth(60);
		        PlayAgain.setPrefHeight(45);
		        PlayAgain.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';");
                PlayAgain.setOnMouseEntered(h -> PlayAgain.setStyle("-fx-background-color:#7FFF00; -fx-font-size: 1em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bell MT';"));
		        PlayAgain.setOnMouseExited(h -> PlayAgain.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';")); 
                LeftButton.getChildren().add(PlayAgain);
                LeftButton.setAlignment(Pos.CENTER_LEFT);

                Exit = new Button("Exit");
                Exit.setPrefWidth(60);
		        Exit.setPrefHeight(45);
                Exit.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';");
                Exit.setOnMouseEntered(h -> Exit.setStyle("-fx-background-color:#FF0000; -fx-font-size: 1em; -fx-text-fill: #FFFFFF; -fx-font-family: 'Bell MT';"));
		        Exit.setOnMouseExited(h -> Exit.setStyle("-fx-background-color:#FFFFFF; -fx-font-size: 1em; -fx-text-fill: #000000; -fx-font-family: 'Bell MT';")); 
                RightButton.getChildren().add(Exit);
                RightButton.setAlignment(Pos.CENTER_RIGHT);
                

                //Clicking the Play Again button
                PlayAgain.setOnAction(new EventHandler<ActionEvent>() {
			        public void handle(ActionEvent e){
				        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesScene.fxml"));
                        try{
                            root = loader.load();
                        }catch(Exception ex){
                            
                        }
                        CategoriesSceneController csc = loader.getController();
                        
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show(); 
                        csc.setUser(username);
			            }
		            });

                //Clicking the exit button
                Exit.setOnAction(e -> System.exit(0));

                
                
            }else if(game.roundWon == 1){  //If player has more rounds to play

                Outcome = new Label("Nice Job!");
                Outcome.setStyle("-fx-font-size: 2em; -fx-font-family: 'Bell MT';");  
                BottomLabel.getChildren().add(Outcome); 
                BottomLabel.setAlignment(Pos.CENTER);

                PauseTransition pause = new PauseTransition(Duration.seconds(2));

		        pause.setOnFinished(e -> {
                    
                    //Go to categories
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesScene.fxml"));
                    try{
                        root = loader.load();
                    }catch(Exception ex){
                            
                    }
                    CategoriesSceneController csc = loader.getController();
                        
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show(); 
                    csc.accept(game);
                    csc.setGame();
                    csc.getClient(client);
                    csc.setUser(username);
                });

                pause.play();

            }
        }

        
    }

    //Separate event handlers for each button refferencing the helper function that handles button functionality

    public void SelectA(ActionEvent event) throws IOException {
        SelectLetter(A, "A", event);
    }

    
    public void SelectB(ActionEvent event) throws IOException {
        SelectLetter(B, "B", event);
    }

    public void SelectC(ActionEvent event) throws IOException {
        SelectLetter(C, "C", event);
    }    
    
    public void SelectD(ActionEvent event) throws IOException {
        SelectLetter(D, "D", event);
    }

    public void SelectE(ActionEvent event) throws IOException {
        SelectLetter(E, "E", event);
    }
    
    public void SelectF(ActionEvent event) throws IOException {
        SelectLetter(F, "F", event);
    }

    public void SelectG(ActionEvent event) throws IOException {
        SelectLetter(G, "G", event);
    }

    public void SelectH(ActionEvent event) throws IOException {
        SelectLetter(H, "H", event);
    }

    public void SelectI(ActionEvent event) throws IOException {
        SelectLetter(I, "I", event);
    }

    public void SelectJ(ActionEvent event) throws IOException {
        SelectLetter(J, "J", event);
    }

    public void SelectK(ActionEvent event) throws IOException {
        SelectLetter(K, "K", event);
    }
    
    public void SelectL(ActionEvent event) throws IOException {
        SelectLetter(L, "L", event);
    }

    public void SelectM(ActionEvent event) throws IOException {
        SelectLetter(M, "M", event);
    }

    public void SelectN(ActionEvent event) throws IOException {
        SelectLetter(N, "N", event);
    }

    public void SelectO(ActionEvent event) throws IOException {
        SelectLetter(O, "O", event);
    }

    public void SelectP(ActionEvent event) throws IOException {
        SelectLetter(P, "P", event);
    }

    public void SelectQ(ActionEvent event) throws IOException {
        SelectLetter(Q, "Q", event);
    }

    public void SelectR(ActionEvent event) throws IOException {
        SelectLetter(R, "R", event);
    }

    public void SelectS(ActionEvent event) throws IOException {
        SelectLetter(S, "S", event);
    }

    public void SelectT(ActionEvent event) throws IOException {
        SelectLetter(T, "T", event);
    }

    public void SelectU(ActionEvent event) throws IOException {
        SelectLetter(U, "U", event);
    }

    public void SelectV(ActionEvent event) throws IOException {
        SelectLetter(V, "V", event);
    }

    public void SelectW(ActionEvent event) throws IOException {
        SelectLetter(W, "W", event);
    }

    public void SelectX(ActionEvent event) throws IOException {
        SelectLetter(X, "X", event);
    }

    public void SelectY(ActionEvent event) throws IOException {
        SelectLetter(Y, "Y", event);
    }

    public void SelectZ(ActionEvent event) throws IOException {
        SelectLetter(Z, "Z", event);
    }
}

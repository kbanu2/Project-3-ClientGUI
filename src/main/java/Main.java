//Main class starts off the program and calls the first scene

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

    @Override
	public void start(Stage primaryStage) throws Exception {

        try{

            Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}
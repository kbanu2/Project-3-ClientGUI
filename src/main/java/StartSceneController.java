//This class controls the events of the Starting Scene of the program and goes to the next scene


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartSceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogInScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    public void Exit(ActionEvent event) throws IOException {
        System.exit(0);
    }


}
 
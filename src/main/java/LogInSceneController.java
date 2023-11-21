//This class controls the events of the Log In Scene of the program and goes to the next scene
//It also connects the client to the server and transfer the data of the username to the Guessing Round scene


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LogInSceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField UserName;
    @FXML
    TextField PortNum;

    Client client;
    

    public void ConnectToServer(ActionEvent event) throws IOException {
        Boolean valid = true;

        // Validate UserName
        String username = UserName.getText().trim();
        if (username.isEmpty() || !isValidUsername(username) || "Enter Valid Username".equals(username)) {
            UserName.setText("Enter Valid Username");
            valid = false;
        }

        // Validate PortNum
        String portText = PortNum.getText().trim();
        if (portText.isEmpty() || !isValidPortNumber(portText) || "Enter Valid Port#".equals(portText)) {
            PortNum.setText("Enter Valid PortNum");
            valid = false;
        }

        if(valid == false){
            return;
        }

        //This is where we initiate the client and connect to the server
        client.start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriesScene.fxml"));
        root = loader.load();
        CategoriesSceneController csc = loader.getController();
        client.setCategoriesController(csc);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        csc.setUser(username);
        csc.InitializeGame();
    }

    //Helper functions that validate username and port#
    
    private boolean isValidUsername(String username) {

        return username.matches("^[a-zA-Z0-9]+$") && !username.matches("^[0-9]+$");
    }

    private boolean isValidPortNumber(String portText) {

        return portText.matches("^[0-9]+$");
    }
}

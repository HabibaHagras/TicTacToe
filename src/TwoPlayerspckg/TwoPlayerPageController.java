/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwoPlayerspckg;

import gameScreen.GameController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.helpcontroller;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TwoPlayerPageController implements Initializable {

    @FXML
    private Rectangle Rectangle;
    @FXML
    private Text txtPlayerO;
    @FXML
    private TextField txtfieldPayerO;
    @FXML
    private Button btnStart;
    @FXML
    private TextField txtfieldPayerX;
    @FXML
    private Text txtPlayerX;
    @FXML
    private ImageView TwoPlayerImg;
    @FXML
    private Text txtVS;
    @FXML
     AnchorPane apane;
    @FXML
    private ImageView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backbtn.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) backbtn.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        });
    }
    
    @FXML
    public void onclickstart(ActionEvent event) throws IOException{    
        String playerX=txtfieldPayerX.getText();
        String playerO=txtfieldPayerO.getText();

        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btnStart && !playerX.isEmpty() && !playerO.isEmpty()){
            stage = (Stage) btnStart.getScene().getWindow();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gameScreen/Game.fxml"));
            myNewScene=loader.load();
            GameController game= loader.getController();
            game.displayPlayers(playerX, playerO);
         

            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();  
        }else{
            Alert missingInfoAlert = new Alert(AlertType.WARNING);
            missingInfoAlert.setTitle("Missing Information");
            missingInfoAlert.setContentText("Please Enter Names for 2 Players.");
            missingInfoAlert.showAndWait();
        }
    }    
}

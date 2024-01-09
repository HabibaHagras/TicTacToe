/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnePlayerpckg;

import computermode.ComputerGameController;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.LevelsPckg.LevelsPageController;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class OnePlayerPageController implements Initializable {
    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    static String playerName = " ";
    @FXML
    private ImageView btnBack;
    @FXML
    private Rectangle Rectangle;
    @FXML
    private Text txtPlayerName;
    @FXML
    private TextField txtfieldPayerName;
    @FXML
    private Button btnStart;
    @FXML
    private ImageView onePlayerImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   
    @FXML
    public void navigateToLevels (ActionEvent event) throws IOException {
                 
        String playerX=txtfieldPayerName.getText();

        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btnStart && !playerX.isEmpty()){
            stage = (Stage) btnStart.getScene().getWindow();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/tictactoe/LevelsPckg/LevelsPage.fxml"));
            myNewScene=loader.load();
            LevelsPageController controller = loader.getController();
            controller.setName(playerX);
         

            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();  
        }else{
            Alert missingInfoAlert = new Alert(Alert.AlertType.WARNING);
            missingInfoAlert.setTitle("Missing Information");
            missingInfoAlert.setContentText("Please Enter Names of Player.");
            missingInfoAlert.showAndWait();
        }
   
    }// 
    
    
    public void navigateToBack (MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(OnePlayerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }
    
    
    public String getPlayerName(){
        
        return playerName;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameScreen;

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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.helpcontroller;


/**
 *
 * @author HP
 */
public class GameController implements Initializable {

    @FXML
    private Text player1;
    @FXML
    private Text player2;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button newButton;
    @FXML
     AnchorPane apane;
      Stage stage = null;
        Parent myNewScene = null;
        
 @Override
 public void initialize(URL url, ResourceBundle rb) {
           
        backBtn.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) backBtn.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));

                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace()
            }
        });
    
        // TODO
    } 

    
          public void onclicknewgame(ActionEvent event) throws IOException{
  
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == newButton){
            stage = (Stage) newButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        } else{System.out.println("gameScreen.GameController.onclicknewgame()");}

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
                

          }
                  
          public void onclicknewgame(ActionEvent event) throws IOException{
  
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == newButton){
            stage = (Stage) newButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        } else{System.out.println("gameScreen.GameController.onclicknewgame()");}

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
                

          }
}
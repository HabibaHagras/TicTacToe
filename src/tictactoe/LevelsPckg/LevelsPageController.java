/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.LevelsPckg;

import OnePlayerpckg.OnePlayerPageController;
import computermode.AdversarialSearchTicTacToe;
import computermode.ComputerGameController;
import gameScreen.GameController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class LevelsPageController implements Initializable {
    Stage stage ;
    Parent myNewScene;
    String player;
    public static String type;
    @FXML
    private Text txtLevels;
    @FXML
    private Button btnEasy;
    @FXML
    private Button btnMedium;
    @FXML
    private Button btnHard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    public void navigateToEasy (ActionEvent event) throws IOException {
        
//         if (event.getSource() == btnEasy) {
//            stage = (Stage) btnEasy.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/computermode/ComputerGame.fxml"));
//            Parent myNewScene = loader.load();
//            ComputerGameController compMode = loader.getController();
//            OnePlayerPageController onePlayer = new OnePlayerPageController();
//            String playName = onePlayer.getPlayerName();
//            System.out.println("Player Name Retrieved: " + playName);
//            compMode.displayPlayerName(playName);
//            Scene scene = new Scene(myNewScene);
//            stage.setScene(scene);
//            stage.setTitle("Tic Tac Toe");
//            stage.show();
//        }
            

        if (event.getSource() == btnEasy) {
            stage = (Stage) btnEasy.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/computermode/ComputerGame.fxml"));
            myNewScene=loader.load();
            ComputerGameController gameController = loader.getController();
            gameController.displayPlayerName(player);
            type = "Easy";
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    @FXML
    public void navigateToMedium (ActionEvent event) throws IOException {
        if (event.getSource() == btnMedium) {
            stage = (Stage) btnMedium.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/computermode/ComputerGame.fxml"));
            myNewScene=loader.load();
            ComputerGameController gameController = loader.getController();
            gameController.displayPlayerName(player);
            type = "Meduim";
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    @FXML
    public void navigateToHard (ActionEvent event) throws IOException {
        if (event.getSource() == btnHard) {
            stage = (Stage) btnHard.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/computermode/ComputerGame.fxml"));
            myNewScene=loader.load();
            ComputerGameController gameController = loader.getController();
            gameController.displayPlayerName(player);
            type = "Hard";
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    
    public void setName(String name){
        player = name;
    }
}

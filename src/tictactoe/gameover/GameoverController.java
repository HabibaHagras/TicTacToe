/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gameover;

import computermode.ComputerGameController;
import gameScreen.GameController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GameoverController implements Initializable {

    Scene scene = null;
    Stage stage = null;
    Parent root = null;
    private GameController gameController;

    @FXML
    private AnchorPane apane;
    @FXML
    private Text Text;
    @FXML
    private Text Text1;
    @FXML
    private Button button;
    private ComputerGameController gamecomputercontroller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    public void startscreenClick(ActionEvent event) throws IOException {
        gameController.closeGameStage();
        closeGameoverStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/StartScreen.fxml"));
        Parent myNewScene = loader.load();
        Scene newScene = new Scene(myNewScene);

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("Start Screen");
        newStage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeGameoverStage() {
        Stage closeGameoverStage = (Stage) apane.getScene().getWindow();
        closeGameoverStage.close();
    }

    public void seComputertGameController(ComputerGameController gamecomputercontroller) {
        this.gamecomputercontroller = gamecomputercontroller;
    }
}

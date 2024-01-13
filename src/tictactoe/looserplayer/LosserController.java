/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.looserplayer;

import computermode.ComputerGameController;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tictactoe.playagainwin.PlayagainwinController;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LosserController implements Initializable {

    Stage stage;
    private String winner;

    @FXML
    private AnchorPane apane;
    @FXML
    private Text Text;
    @FXML
    private MediaView MediaFile;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button button1yes;
    @FXML
    private Button button1no;
    @FXML
    private Text Text1;
    @FXML
    private Label labelplayer;
    private ComputerGameController gamecomputercontroller;
    Parent root = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = PlayagainwinController.class.getResource("losser.mp4");
        Media media = new Media(url.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        MediaFile.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
                mediaPlayer.stop();

            }
        });
        mediaPlayer.setOnError(
                new Runnable() {
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
        );
        // TODO
    }

    @FXML
    private void onclickplayagain(ActionEvent event) {

        gamecomputercontroller.resetButton(gamecomputercontroller.button1);
        gamecomputercontroller.resetButton(gamecomputercontroller.button2);
        gamecomputercontroller.resetButton(gamecomputercontroller.button3);
        gamecomputercontroller.resetButton(gamecomputercontroller.button4);
        gamecomputercontroller.resetButton(gamecomputercontroller.button5);
        gamecomputercontroller.resetButton(gamecomputercontroller.button6);
        gamecomputercontroller.resetButton(gamecomputercontroller.button7);
        gamecomputercontroller.resetButton(gamecomputercontroller.button8);
        gamecomputercontroller.resetButton(gamecomputercontroller.button9);
        stage.close();

    }

    @FXML
    private void onclickNo(ActionEvent event) {
        try {
            gamecomputercontroller.closeComputerGameStage();
            closelOSSERStage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/StartScreen.fxml"));
            Parent myNewScene = loader.load();
            Scene newScene = new Scene(myNewScene);

            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle("Start Screen");
            newStage.show();
            // mediaPlayer.stop();
        } catch (IOException ex) {
            Logger.getLogger(LosserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closelOSSERStage() {
        Stage losserStage = (Stage) apane.getScene().getWindow();
        losserStage.close();
    }

    public void seComputertGameController(ComputerGameController gamecomputercontroller) {
        this.gamecomputercontroller = gamecomputercontroller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setWinner(String winner) {
        this.winner = winner;
        labelplayer.setText(winner);
        System.out.println(winner);
    }
}

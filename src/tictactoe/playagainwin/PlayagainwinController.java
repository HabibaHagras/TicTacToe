/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.playagainwin;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PlayagainwinController implements Initializable {

    Scene scene = null;
    Stage stage;
    Parent root = null;
    @FXML
    private AnchorPane apane;
    @FXML
    private Text Text;
    @FXML
    private Text Text1;
    @FXML
    private MediaView MediaFile;
    private MediaPlayer mediaPlayer;
    private String winner;
    @FXML
    private Button button1yes;
    @FXML
    private Button button1no;
    @FXML
    private Label labelplayer;
    private GameController gameController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = PlayagainwinController.class.getResource("videoplayback.mp4");
        Media media = new Media(url.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        MediaFile.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
        mediaPlayer.setOnError(
                new Runnable() {
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        // TODO
    }

    public void setWinner(String winner) {

        //String playername = GameController.winner; 
        this.winner = winner;
        labelplayer.setText(winner);
        System.out.println(winner);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onclickplayagain(ActionEvent event) {
        gameController.resetButton(gameController.button1);
        gameController.resetButton(gameController.button2);
        gameController.resetButton(gameController.button3);
        gameController.resetButton(gameController.button4);
        gameController.resetButton(gameController.button5);
        gameController.resetButton(gameController.button6);
        gameController.resetButton(gameController.button7);
        gameController.resetButton(gameController.button8);
        gameController.resetButton(gameController.button9);

        stage.close();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    public void onclickNo(ActionEvent event) throws IOException {

        gameController.closeGameStage();
        closePlayagainwinStage();          // Close the Playagainwin stage

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/StartScreen.fxml"));
        Parent myNewScene = loader.load();
        Scene newScene = new Scene(myNewScene);

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("Start Screen");
        newStage.show();

//        Stage stage = null;
//        Parent myNewScene = null;
//
//        if (event.getSource() == button1no) {
//            stage = (Stage) button1no.getScene().getWindow();
//            // myNewScene = FXMLLoader.load(getClass().getResource("/gameScreen/Game.fxml"));
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/StartScreen.fxml"));
//            myNewScene = loader.load();
//            Scene scene = new Scene(myNewScene);
//            stage.setScene(scene);
//            stage.setTitle("My New Scene");
//            stage.show();
//
//        } else {
//            System.out.println("gameScreen.GameController.onclicknewgame()");
//        }
    }

    public void closePlayagainwinStage() {
        Stage playagainwinStage = (Stage) apane.getScene().getWindow();
        playagainwinStage.close();
    }
}

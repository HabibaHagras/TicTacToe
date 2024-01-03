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
    Stage stage ;
    Parent root = null;
    @FXML
    private AnchorPane apane;
    @FXML
    private Text Text;
    @FXML
    private Button button1;
    @FXML
    private Text Text1;
    @FXML
    private Text player;
    @FXML
    private MediaView MediaFile;
    private MediaPlayer mediaPlayer;

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

    public void getname() {

        String playername = GameController.winner;
        System.out.println(playername);

    }

    public void onclickplayagain(ActionEvent event) throws IOException {

       
        stage.close();
    }

}

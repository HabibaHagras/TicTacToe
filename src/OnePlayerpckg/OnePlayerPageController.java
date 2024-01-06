/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnePlayerpckg;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class OnePlayerPageController implements Initializable {
    Stage stage ;
    Parent myNewScene;
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
        if (event.getSource() == btnStart) {
            stage = (Stage) btnStart.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/LevelsPckg/LevelsPage.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    
            
    @FXML
    public void navigateToBack (ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            stage = (Stage) btnBack.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    
}

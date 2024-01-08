/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnePlayerpckg;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class OnePlayerPageController implements Initializable {
    Scene scene = null;
    Stage stage = null;
    Parent root = null;
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
            root = FXMLLoader.load(getClass().getResource("/tictactoe/LevelsPckg/LevelsPage.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
    
    
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
    
}

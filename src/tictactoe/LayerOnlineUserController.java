/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LayerOnlineUserController implements Initializable {

    Stage stage;
    Parent myNewScene;
    @FXML
    private AnchorPane apane;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onClickOnlineUsers(ActionEvent event) {

        if (event.getSource() == button) {
            try {
                stage = (Stage) button.getScene().getWindow();
                myNewScene = FXMLLoader.load(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
}

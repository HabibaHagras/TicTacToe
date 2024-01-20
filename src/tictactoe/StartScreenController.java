/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.event.MouseEvent;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StartScreenController implements Initializable {

    Stage stage;
    Parent myNewScene;

    @FXML
    private Text toe;
    @FXML
    private Button rec;
    @FXML
    private Text tac;
    @FXML
    private Text tic;
    @FXML
    GridPane gpane;
    @FXML
    private Button buttonComputer;
    @FXML
    private Button buttonLocal;
    @FXML
    private Button buttonOnline;
    @FXML
    private Button helpIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void navigateToHelp(ActionEvent event) throws IOException {
        if (event.getSource() == helpIcon) {
            stage = (Stage) helpIcon.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/HelpScreen/Help.fxml"));

            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("Tic Tac Toe");
            stage.show();
        }
    }

    @FXML
    public void navigateToLocal(ActionEvent event) throws IOException {
        if (event.getSource() == buttonLocal) {
            stage = (Stage) buttonLocal.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    @FXML
    public void navigateToComputer(ActionEvent event) throws IOException {
        if (event.getSource() == buttonComputer) {
            stage = (Stage) buttonComputer.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/OnePlayerpckg/OnePlayerPage.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    @FXML
    public void navigateToOnline(ActionEvent event) throws IOException {
        if (event.getSource() == buttonOnline) {
            stage = (Stage) buttonOnline.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("login.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    
    @FXML
    public void navigateToRecord (ActionEvent event) throws IOException {
        if (event.getSource() == rec) {
            stage = (Stage) rec.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/ListOfRecords/Records.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tictactoe.FXMLDocumentController;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
     AnchorPane apane;
    @FXML
    private Rectangle Rectangle;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField feild;
    @FXML
    private TextField feild1;
    @FXML
    private Button buttonlogin;
    @FXML
    private Button buttonregistration1;
    @FXML
    private Text Text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void setaction(ActionEvent event) {
        
        AnchorPane pane;
       
        try {
            pane = FXMLLoader.load(getClass().getResource("login.fxml"));
                           apane.getChildren().setAll(pane);
                           
                           System.out.println("cliiiiiicked");

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                       System.out.println("cliiiiiicked");

        }
       
                           System.out.println("cliiiiiicked");

}

    @FXML
    private void setaction1(ActionEvent event) {
           AnchorPane pane;
       
        try {
            pane = FXMLLoader.load(getClass().getResource("login.fxml"));
                           apane.getChildren().setAll(pane);
                           
                           System.out.println("cliiiiiicked");

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                       System.out.println("cliiiiiicked");

        }
       
                           System.out.println("cliiiiiicked");

    }
    
}

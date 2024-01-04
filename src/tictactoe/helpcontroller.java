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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author HP
 */
public class helpcontroller implements Initializable {
    
    private Label label;
    @FXML
    private Text txt;
    ImageView backBtn;
    @FXML
    private AnchorPane apane;
    @FXML
    private ImageView backbtn;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle(MouseEvent event) {
                          GridPane pane;
       
        try {
            pane = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
                           apane.getChildren().setAll(pane);

        } catch (IOException ex) {
            Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }}
    }
    

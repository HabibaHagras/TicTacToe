/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwoPlayerspckg;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.helpcontroller;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TwoPlayerPageController implements Initializable {

    @FXML
    private Rectangle Rectangle;
    @FXML
    private Text txtPlayerO;
    @FXML
    private TextField txtfieldPayerO;
    @FXML
    private Button btnStart;
    @FXML
    private TextField txtfieldPayerX;
    @FXML
    private Text txtPlayerX;
    @FXML
    private ImageView TwoPlayerImg;
    @FXML
    private Text txtVS;
    @FXML
     AnchorPane apane;
    @FXML
    private ImageView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { backbtn.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) backbtn.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));

                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });
        
        // TODO
    }    
    @FXML
    public void onclickstart(ActionEvent event) throws IOException{
        
        
        
        
             Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btnStart){
            stage = (Stage) btnStart.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/gameScreen/Game.fxml"));
        } else{System.out.println("gameScreen.GameController.onclicknewgame()");}

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
        
        
//            try {
//        // Load the FXML file
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gameScreen/Game.fxml"));
//
//        // Verify if gpane is initialized before setting its children
//        if (apane != null) {
//            // Clear the existing children and set the new pane
//            apane.getChildren().setAll(pane);
//        } else {
//            System.err.println("Error: gpane is not initialized.");
//        }
//
//    } catch (IOException ex) {
//        Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
//    }
//            
}
       public void onclicknewgame(){
        
           
           
//           
//           try {
//        // Load the FXML file
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
//
//        // Verify if gpane is initialized before setting its children
//        if (apane != null) {
//            // Clear the existing children and set the new pane
//            apane.getChildren().setAll(pane);
//        } else {
//            System.err.println("Error: gpane is not initialized.");
//        }
//
//    } catch (IOException ex) {
//        Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
//    }
            
}
    
}

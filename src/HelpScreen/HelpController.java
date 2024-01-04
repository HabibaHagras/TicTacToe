/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpScreen;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.helpcontroller;

/**
 * FXML Controller class
 *
 * @author HP
 */

public class HelpController implements Initializable {
   
    @FXML
     AnchorPane apane;
    @FXML
    private Text txt;
    @FXML
    private ImageView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        backbtn.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
                Parent myNewScene = loader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
                stage.getScene().setRoot(myNewScene);
//                Scene scene = new Scene(myNewScene);
//                stage.setScene(scene);
//                stage.setTitle("My New Scene");
//                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });
    
        // TODO
    }  
        private void handle(MouseEvent event) {
//         Your event handling logic here
                   GridPane pane;
       
        try {
        FXMLLoader loader = new FXMLLoader();
            pane = loader.load(getClass().getResource("StartScreen.fxml"));
                           apane.getChildren().setAll(pane);

        } catch (IOException ex) {
            Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }}
      public void onclicknewgame(ActionEvent event) throws IOException{
  
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == backbtn){
            stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
            myNewScene = loader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
        } else{System.out.println("gameScreen.GameController.onclicknewgame()");}

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
                

          }
      
      public void setOnMousePressed(ActionEvent event) throws IOException{
   try {
                    Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
                    Parent myNewScene = loader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));

                    Scene scene = new Scene(myNewScene);
                    stage.setScene(scene);
                    stage.setTitle("My New Scene");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
        
          
      
}

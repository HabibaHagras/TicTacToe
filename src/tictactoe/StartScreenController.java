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

    @FXML
    private Text toe;
    @FXML
    private Button rec;
    @FXML
    private Text tac;
    @FXML
    private Text tic;
    @FXML
     Button buttonlocal;
    @FXML
     GridPane gpane;
    @FXML
     Button helpicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void onclick(ActionEvent event)throws IOException{
        
             Stage stage = null;
        Parent myNewScene = null;

       if (event.getSource() == helpicon){
            stage = (Stage) helpicon.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
            myNewScene = loader.load(getClass().getResource("/HelpScreen/Help.fxml"));

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
                
        
        
//            try {
//        // Load the FXML file
//                BorderPane pane = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
//
//        // Verify if gpane is initialized before setting its children
//        if (gpane != null) {
//            // Clear the existing children and set the new pane
//            gpane.getChildren().setAll(pane);
//        } else {
//            System.err.println("Error: gpane is not initialized.");
//        }
//
//    } catch (IOException ex) {
//        Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
//    }

//            AnchorPane pane;
//       
//        try {
//            pane = FXMLLoader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
//                           gpane.getChildren().setAll(pane);
//
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }}
    
    
    
    //////////////////////////////////////////////////////////////
      public void onclick1(ActionEvent event)throws IOException{
        
             Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == buttonlocal){
            stage = (Stage) buttonlocal.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
            myNewScene = loader.load(getClass().getResource("/TwoPlayerspckg/TwoPlayerPage.fxml"));
        } 
stage.getScene().setRoot(myNewScene);
//        Scene scene = new Scene(myNewScene);
//        stage.setScene(scene);
//        stage.setTitle("My New Scene");
//        stage.show();
                
        
    }

//////////////////////////////////
          public void onclick2(ActionEvent event)throws IOException{
              System.out.println("help btn is clicked");
             Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == helpicon){
              System.out.println("help btn start load");
            stage = (Stage) helpicon.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
            myNewScene = loader.load(getClass().getResource("/HelpScreen/Help.fxml"));
        } 
        
              System.out.println("help btn scence created");
        stage.getScene().setRoot(myNewScene);
              System.out.println("help btn scence loaded");

//        Scene scene = new Scene(myNewScene);
//        stage.setScene(scene);
//        stage.setTitle("My New Scene");
//        stage.show();
                
        
    }






}
    
    

        
        
  
            
   
      

   

    

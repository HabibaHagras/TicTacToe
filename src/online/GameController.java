///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package online;
//
//import java.io.DataInputStream;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//
///**
// *
// * @author HP
// */
//// Modify GameController class
//public class GameController implements Initializable {
//    private Socket clientSocket;
//    private DataInputStream ear;
//    private PrintStream mouth;
//
//    // Other members...
//
//  
//
//    private void setupButton(Button button) {
//        button.setOnMouseClicked(mouseEvent -> {
//            setPlayerSymbol(button);
//            button.setDisable(true);
//            
//            // Send the move to the server
//            String move = button.getId(); // Use the button ID as a move identifier
//            mouth.println(move);
//        });
//    }
//
//    // Other methods...
//
//    // Add a method to handle moves received from the server
//    private void handleServerMove(String move) {
//        // Process the move received from the server
//        // Update the game state accordingly
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}

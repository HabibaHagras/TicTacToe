///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package onlineUserScrren;
//
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.text.Text;
//import javax.swing.text.html.ListView;
//
///**
// * FXML Controller class
// *
// * @author Chicoo
// */
//public class OnlineUserController implements Initializable {
//
//    @FXML
//    private Text username;
//    @FXML
//    private Text point;
//    @FXML
//    private javafx.scene.control.ListView<DTO> onlinePlayersListView;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//
//    @FXML
//
//    public void updateOnlinePlayersList(ArrayList<DTO> onlinePlayers) {
//       System.out.println(onlinePlayers.getClass());
//        onlinePlayersListView.getItems().setAll(onlinePlayers);
//        
//    }
//
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineUserScrren;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.text.html.ListView;
import online.OnlinemodeController;
import tictactoe.LoginController;
import static tictactoe.LoginController.stagenow;

/**
 * FXML Controller class
 *
 * @author Chicoo
 */
public class OnlineUserController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text point;
    @FXML
    private javafx.scene.control.ListView<String> onlinePlayersListView;
    Socket s;
    Socket server;
    DataInputStream ear;
    PrintStream mouth;
    ArrayList<String> onlinePplayers;
    @FXML
    private Text text0;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Button invaite1;
    @FXML
    private Button invaite2;
    @FXML
    private Button invite0;
    String UserInvitatonTO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("OnlineUserController initialized");

    }

    public void updateOnlinePlayersList(ArrayList<String> onlinePlayers) {
        Platform.runLater(() -> {

            List<String> tokenizedPlayers = onlinePlayers.stream()
                    .flatMap(player -> Pattern.compile(",").splitAsStream(player))
                    .map(String::trim) // Remove leading and trailing whitespaces
                    .filter(part -> !part.isEmpty()) // Filter out empty parts
                    .collect(Collectors.toList());
            text0.setText(tokenizedPlayers.get(0));
            text1.setText(tokenizedPlayers.get(1));

            //   System.out.println(tokenizedPlayers.get(0));
            //   System.out.println(onlinePlayers.getClass());
            onlinePlayersListView.getItems().setAll(tokenizedPlayers);

        });

        System.out.println(
                "onlineUserScrren.OnlineUserController.updateOnlinePlayersList()" + "onlinePlayers");
        onlinePlayersListView.setOnMouseClicked(event
                -> {
            // Get the selected item
            String selectedItem = onlinePlayersListView.getSelectionModel().getSelectedItem();
            new Thread(() -> {
                try {
                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    OutputStream outputStream = server.getOutputStream();
                    InputStream inputStream = server.getInputStream();

                    String inviteMessage = "invite" + " " + selectedItem + " " + "1234";
                    System.out.println(inviteMessage);
                    outputStream.write(inviteMessage.getBytes());
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
                    Parent onlineGamePage = loaderr.load();
                    OnlinemodeController onlineGameController = loaderr.getController();

                    byte[] responseBuffer = new byte[1024];
                    int responseBytes = inputStream.read(responseBuffer);
                    String serverResponse = new String(responseBuffer, 0, responseBytes);
                    System.out.println("Server response: " + serverResponse);
                    StringTokenizer tokenizer = new StringTokenizer(serverResponse);

                    String command = tokenizer.nextToken();

// Assuming the second token is the username
                    String username = tokenizer.nextToken();

// Assuming the third token is the additional information (e.g., "111")
                    String additionalInfo = tokenizer.nextToken();
                    if (command.equals("userfound")) {
                        System.out.println("USERFOUND");
                        Platform.runLater(() -> {
                     Alert waitingAlert = new Alert(Alert.AlertType.INFORMATION);
                            waitingAlert.setTitle("Waiting");
                            waitingAlert.setHeaderText("Please wait...");
                            waitingAlert.setContentText("Performing some task. Please wait...");

                            waitingAlert.showAndWait().ifPresent(response -> {
                            
                                    System.out.println("User clicked OK");
                                    
                                        System.out.println("WaitingAccpeted online user");

                                        Platform.runLater(() -> {
                                            Stage stage = new Stage();
                                            Scene scene = new Scene(onlineGamePage);
                                            stage.setScene(scene);
                                            stage.setTitle("ONLINEGamegor online user" );
                                            stage.show();

                                        });
                                    
                                
                            });
                            
                             });
        } 

                        
                        
                    
                    
                } catch (UnknownHostException ex) {
                    Logger.getLogger(OnlineUserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OnlineUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
            // Perform the desired action with the selected item
            if (selectedItem != null) {
                // Example: Display a message with the selected item
                System.out.println("Selected item: " + selectedItem);
            }
        }
        );
    }

   

    public void setloginName(String login_name) {
        System.out.println("LOGIN NAME" + login_name);
        this.UserInvitatonTO = login_name;
    }
     private void navigateAfterAccept() throws IOException {
        Platform.runLater(() -> {
            System.out.println("tictactoe.LoginController.navigateAfterAccept()");
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
                Parent myNewScene = loader.load();
                Scene scene = new Scene(myNewScene);
                 stagenow = new Stage();

                //stagenow = TicTacToe.getStage();
                stagenow.setScene(scene);
                stagenow.setTitle("game");
                stagenow.show();
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            }
        });
    }

}

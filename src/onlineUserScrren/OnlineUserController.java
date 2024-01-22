
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.text.html.ListView;
import online.OnlinemodeController;

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
    String UserInvitatonTO;
    String WaitingAccpetedPART;
    String WaitingAccpeted_user;
    private Alert waitingAlert;
    String symbol;
    @FXML
    private AnchorPane apane;
    @FXML
    private Button logout;
    @FXML
    private ImageView btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) btnBack.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("OnlineUserController initialized");

    }

    public void updateOnlinePlayersList(String loginName, ArrayList<String> onlinePlayers) {
        Platform.runLater(() -> {
            // List<String> onlinePlayersList = new ArrayList<>(onlinePlayers);
            List<String> tokenizedPlayers = onlinePlayers.stream()
                    .flatMap(player -> Pattern.compile(",").splitAsStream(player))
                    .map(String::trim) // Remove leading and trailing whitespaces
                    .filter(part -> !part.isEmpty()) // Filter out empty parts
                    .filter(part -> !part.equals(loginName)) // Filter out the entered login name
                    .collect(Collectors.toList());
//        text0.setText(tokenizedPlayers.get(0));
//        text1.setText(tokenizedPlayers.get(1));
            for (String player : onlinePlayers) {
                StringTokenizer tokenizer = new StringTokenizer(player);

                if (tokenizer.hasMoreTokens()) {
                    String firstPart = tokenizer.nextToken();
                    if (firstPart.equals("UserAccpeted") && tokenizer.hasMoreTokens()) {
                        WaitingAccpetedPART = firstPart;
                        WaitingAccpeted_user = tokenizer.nextToken();

                        System.out.println("WaitingAccpeted part: " + WaitingAccpetedPART);
                        System.out.println("WaitingAccpeted user: " + WaitingAccpeted_user);

                    }
                    if (firstPart.equals("WaitingAccpeted") && tokenizer.hasMoreTokens()) {
                        WaitingAccpetedPART = firstPart;
                        WaitingAccpeted_user = tokenizer.nextToken();

                        System.out.println("WaitingAccpeted part: " + WaitingAccpetedPART);
                        System.out.println("WaitingAccpeted user: " + WaitingAccpeted_user);

                    }
                    if (firstPart.equals("X")) {
                        symbol = firstPart;
                        // WaitingAccpeted_user = tokenizer.nextToken();

                        System.out.println("symbol part: " + symbol);

                    }

                    System.out.println("First part: " + firstPart);
                } else {
                    System.out.println("Invalid input");
                }
            }

            //   System.out.println(tokenizedPlayers.get(0));
            //   System.out.println(onlinePlayers.getClass());
            onlinePlayersListView.getItems().setAll(tokenizedPlayers);
        });
        System.out.println("onlineUserScrren.OnlineUserController.updateOnlinePlayersList()" + "onlinePlayers");
        onlinePlayersListView.setOnMouseClicked(event -> {
            // Get the selected item
            String selectedItem = onlinePlayersListView.getSelectionModel().getSelectedItem();
            new Thread(() -> {
                try {

                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    OutputStream outputStream = server.getOutputStream();
                    InputStream inputStream = server.getInputStream();

                    String inviteMessage = "invite" + " " + selectedItem + " " + "1234"+" "+"123";
                    System.out.println(inviteMessage);
                    outputStream.write(inviteMessage.getBytes());
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
                    Parent onlineGamePage = loaderr.load();
                    OnlinemodeController onlineGameController = loaderr.getController();
                    onlineGameController.setPlayer2Name(selectedItem);
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
//                        if (username.equals(UserInvitatonTO)) {
/*
                        Platform.runLater(() -> {
                            Alert waitingAlert = new Alert(Alert.AlertType.INFORMATION);
                            waitingAlert.setTitle("Waiting");
                            waitingAlert.setHeaderText("Please wait...");
                            waitingAlert.setContentText("Performing some task. Please wait...");

                            waitingAlert.showAndWait().ifPresent(response -> {
                                if (WaitingAccpetedPART.equals("WaitingAccpeted")) {
                                    System.out.println("WaitingAccpeted online user");

                                    Platform.runLater(() -> {
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(onlineGamePage);
                                        stage.setScene(scene);
                                        stage.setTitle("ONLINEGamegor online user" + WaitingAccpeted_user);
                                        stage.show();

                                        // Close the alert immediately
                                        waitingAlert.close();
                                    });
                                }
                         */
//                            Alert inviteAlert = new Alert(Alert.AlertType.INFORMATION);
//                            inviteAlert.setTitle("Wating");
//                            inviteAlert.setHeaderText("Waaaaiting");
//                            inviteAlert.setContentText("Waaaaiting");
//                            inviteAlert.showAndWait();
/*
                            Alert waitingAlert = new Alert(Alert.AlertType.INFORMATION);

                            // Set the title and header text
                            waitingAlert.setTitle("Waiting");
                            waitingAlert.setHeaderText("Please wait...");

                            // Set the content text
                            waitingAlert.setContentText("Performing some task. Please wait...");

                            // Create the "OK" button
                            ButtonType okButton = new ButtonType("OK");

                            // Add the button to the alert
                            waitingAlert.getButtonTypes().setAll(okButton);

                            waitingAlert.showAndWait().ifPresent(response -> {
                                if (response == okButton) {
                                    System.out.println("User clicked OK");
                                    if (WaitingAccpetedPART.equals("WaitingAccpeted")) {
                                        System.out.println("WaitingAccpeted online user");

                                        Platform.runLater(() -> {
                                            Stage stage = new Stage();
                                            Scene scene = new Scene(onlineGamePage);
                                            stage.setScene(scene);
                                            stage.setTitle("ONLINEGamegor online user" + WaitingAccpeted_user);
                                            stage.show();

                                        });
                                    }
                                }
                            });
                         */
                        // Optional: You can also set an event handler for the "OK" button
                        // waitingAlert.setOnCloseRequest(event -> {
                        //     System.out.println("User closed the alert");
                        // });
                        // Optional: If you want to perform some action after the alert is closed
                        // waitingAlert.setOnHidden(event -> {
                        //     System.out.println("Alert is closed");
                        // });
//                            });
                        /*        });*/

                        if (symbol.equals("X")) {
                            System.out.println("X online user");

                            // onlineGameController.setSymol(symbol);
                        }
                        /*    }); */
                    }
                    if (command.equals("X")) {
                        System.out.println("X online user");

//                        onlineGameController.setSymol(symbol);
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
        });
    }

    public void setloginName(String login_name) {
        System.out.println("LOGIN NAME" + login_name);
        this.UserInvitatonTO = login_name;
        username.setText(login_name);
    }

    public void closeONlineUserStage() {
        Stage gameStage = (Stage) apane.getScene().getWindow();
        gameStage.close();
    }

    @FXML
    private void OnClickLogout(ActionEvent event) throws IOException {
        server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);

        //  String enteredUsername = player1.getText();
        OutputStream outputStream = server.getOutputStream();
        InputStream inputStream = server.getInputStream();
        String msg = "LOGOUT" + " " + UserInvitatonTO + " " + "1234"+" "+"123";
        System.out.println(msg);

        outputStream.write(msg.getBytes());
        System.out.println("feild: " + UserInvitatonTO);
        byte[] responseBuffer = new byte[1024];
        int responseBytes = inputStream.read(responseBuffer);
        String serverResponse = new String(responseBuffer, 0, responseBytes);
        System.out.println("Server response: " + serverResponse);
        if (serverResponse.equals("LOGOUT succeed")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/login.fxml"));
            Parent onlinePlayersPage = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(onlinePlayersPage);
            stage.setScene(scene);
            stage.setTitle("TicTacToe");
            stage.show();
            System.out.println("LOGOUT  process!");
            System.out.println("LOGOUT successful!");
        }

    }

    public String setPlayer2Name() {
    
        return WaitingAccpeted_user;
     
    }

}


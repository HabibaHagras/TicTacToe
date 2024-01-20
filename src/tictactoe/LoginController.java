/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import online.OnlinemodeController;
import onlineUserScrren.OnlineUserController;
//import tictactoeserver.ClientConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    public static Stage stagenow;
    Parent myNewScene;
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
    Socket s;
    Socket server;
    FXMLLoader loader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("tictactoe.LoginController.initialize()");
    }

    private void setaction(ActionEvent event) {

        AnchorPane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("login.fxml"));
            apane.getChildren().setAll(pane);

            System.out.println("cliiiiiicked");

        } catch (IOException ex) {
            // Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("cliiiiiicked");

        }

        System.out.println("cliiiiiicked");

    }

    public void setStage(Stage stage) {
        this.stagenow = stage;
    }

    @FXML
    private void setaction1(ActionEvent event) {
        AnchorPane pane;
        String enteredUsername = feild.getText();
        String enteredPassword = feild1.getText();
        new Thread(() -> {
            try {
                server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                OutputStream outputStream = server.getOutputStream();
                InputStream inputStream = server.getInputStream();

                String msg = "login" + " " + enteredUsername + " " + enteredPassword;
                System.out.println(msg);
                outputStream.write(msg.getBytes());

                byte[] responseBuffer = new byte[1024];
                int responseBytes = inputStream.read(responseBuffer);
                String serverResponse = new String(responseBuffer, 0, responseBytes);
                System.out.println("Server response: " + serverResponse);

                if (serverResponse.equals("login succeed")) {
                    ArrayList<String> onlinePlayers = new ArrayList<>();
                    loader = new FXMLLoader(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));

                    // Load the OnlineUserController and get its controller instance
                    Parent onlinePlayersPage = loader.load();
                    OnlineUserController onlineUserController = loader.getController();
                    onlineUserController.setloginName(enteredUsername);

                    while ((responseBytes = inputStream.read(responseBuffer)) != -1) {
                        String onlineUser = new String(responseBuffer, 0, responseBytes);
                        if (onlineUser.isEmpty()) {
                            System.out.println("Online user: " + "eeeeeeeeepty");
                            break;
                        }

                        onlinePlayers.add(onlineUser);
                        System.out.println("Online user: " + onlineUser);
                        System.out.println("noooooow");
                        onlineUserController.updateOnlinePlayersList(onlinePlayers);
                        StringTokenizer tokenizer = new StringTokenizer(onlineUser);

                        String command = tokenizer.nextToken();

                        // String username = tokenizer.nextToken();
                        //   String additionalInfo = tokenizer.nextToken();
                        if (command.equals("userfound")) {
                            //arrString[] = showAlertForInvitedPlayer , username p1 , username p2
                             
                            ButtonType Accept = new ButtonType("Accept");
                            ButtonType Reject = new ButtonType("Reject");

                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.NONE, "", Accept, Reject);
                                alert.setTitle("Invitation");
                                alert.setContentText("Do you want to accept?");
                                Window window = alert.getDialogPane().getScene().getWindow();
                                window.setOnCloseRequest(e -> alert.hide());

                                Optional<ButtonType> result = alert.showAndWait();
                                result.ifPresent(res -> {
                                    if (res.equals(Accept)) {
                                        
                                        new Thread(() -> {
                                            try {
                                                String msg1 = "Accept" + " " + enteredUsername + " " + enteredPassword;
                                                System.out.println(msg1);
                                                outputStream.write(msg1.getBytes());
                                                outputStream.flush();
                                                // Navigate to the game screen for both users

                                        // For the sender
//                                        FXMLLoader gameLoaderSender = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
//                                        Parent gameScreenSender = gameLoaderSender.load();
//                                        OnlinemodeController gameControllerSender = gameLoaderSender.getController();
//                                        gameControllerSender.setPlayers(enteredUsername, "InvitingPlayer"); // Pass usernames
//                                        Scene gameSceneSender = new Scene(gameScreenSender);
//                                        Stage gameStageSender = new Stage();
//                                        gameStageSender.setScene(gameSceneSender);
//                                        gameStageSender.setTitle("TicTacToe Game (Sender)");
//                                        gameStageSender.show();
//
//                                        // For the receiver
//                                        FXMLLoader gameLoaderReceiver = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
//                                        Parent gameScreenReceiver = gameLoaderReceiver.load();
//                                        OnlinemodeController gameControllerReceiver = gameLoaderReceiver.getController();
//                                        gameControllerReceiver.setPlayers(enteredUsername, "InvitedPlayer"); // Pass usernames
//                                        Scene gameSceneReceiver = new Scene(gameScreenReceiver);
//                                        Stage gameStageReceiver = new Stage();
//                                        gameStageReceiver.setScene(gameSceneReceiver);
//                                        gameStageReceiver.setTitle("TicTacToe Game (Receiver)");
//                                        gameStageReceiver.show();

                                                
                                            } catch (IOException ex) {
                                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }).start();
                                        
                                    } else if (res.equals(Reject)) {
                                        try {
                                            String msg2 = "Reject" + " " + enteredUsername + " " + enteredPassword;
                                            System.out.println(msg2);
                                            outputStream.write(msg2.getBytes());
                                        } catch (IOException ex) {
                                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                            });
                            
                             

//                            Platform.runLater(() -> {
//                                Alert inviteAlert = new Alert(Alert.AlertType.INFORMATION);
//                                inviteAlert.setTitle("Invitation");
//                                inviteAlert.setHeaderText("You've received an invitation!");
//                                inviteAlert.setContentText("Do you want to accept?");
//                               // inviteAlert.showAndWait();
//                                                    ButtonType result = inviteAlert.showAndWait().orElse(ButtonType.CANCEL);
//
//                                 if (result == ButtonType.OK) {
//                                    try {
//                                        // User clicked "OK"
//                                        System.out.println("OK button clicked");
//                                        
//                                        // Add your logic for handling the "OK" button click here
//                                        
//                                        // loader = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
//                                       myNewScene = FXMLLoader.load(getClass().getResource("/online/onlinemode.fxml"));
//                                                           OnlinemodeController omc = loader.getController();
//
//                                        //Parent myNewScene1 = loader1.load();
//                                          Scene scene = new Scene(myNewScene);
//                            //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//                                        stagenow.setScene(scene);
//                                       stagenow.setTitle("Tic Tac Toe");
//                                       stagenow.show();
//
//                                           } catch (IOException ex) {
//                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
//        } else {
//            // User closed the dialog without clicking "OK"
//            System.out.println("Dialog closed without clicking OK");
//        }
//
//
//                            
//                                
//
//                            });
                        }
//                        if(command.equals("twoPlayers")){
//                                 navigateAfterAccept();
//                        
//                    }
                       else {
                            Platform.runLater(() -> {
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(onlinePlayersPage);
                                stage.setScene(scene);
                                stage.setTitle("TicTacToe");
                                stage.show();

                            });
                        }
                    }

                    System.out.println("Login successful!");
                }
            } catch (IOException ex) {
                printStackTrace(ex);
            }
        }).start();
    }

    @FXML

    private void setNavigateregistraton(ActionEvent event) throws IOException {
        if (event.getSource() == buttonregistration1) {
            stagenow = (Stage) buttonregistration1.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("signup.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stagenow.setScene(scene);
        stagenow.setTitle("Tic Tac Toe");
        stagenow.show();
    }

//    private void navigateAfterAccept() throws IOException {
//        Platform.runLater(() -> {
//            System.out.println("tictactoe.LoginController.navigateAfterAccept()");
//            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
//
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
//                Parent myNewScene = loader.load();
//                Scene scene = new Scene(myNewScene);
//                 stagenow = new Stage();
//
//                //stagenow = TicTacToe.getStage();
//                stagenow.setScene(scene);
//                stagenow.setTitle("game");
//                stagenow.show();
//            } catch (IOException e) {
//                e.printStackTrace(); // Handle or log the exception as needed
//            }
//        });
//    }

}

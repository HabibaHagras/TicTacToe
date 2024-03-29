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
import javafx.scene.control.*;
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
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import online.OnlinemodeController;
import onlineUserScrren.OnlineUserController;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    Stage stagenow;
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
    @FXML
    private ImageView back;
    OutputStream outputStream;
    InputStream inputStream;
    String Userlogin;
    String slckted_name;
    String from_name;
    int boll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) back.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        });

        System.out.println("tictactoe.LoginController.initialize()");

    }

    public void setStage(Stage stage) {
        this.stagenow = stage;
    }

    @FXML
    private void setaction1(ActionEvent event) {
        // AnchorPane pane;
        String enteredUsername = feild.getText();
        String enteredPassword = feild1.getText();

        new Thread(() -> {
            try {
                server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                outputStream = server.getOutputStream();
                inputStream = server.getInputStream();
                String msg = "login" + " " + enteredUsername + " " + enteredPassword + " " + "123456";
                System.out.println(msg);
                outputStream.write(msg.getBytes());
                byte[] responseBuffer = new byte[1024];
                int responseBytes = inputStream.read(responseBuffer);
                String serverResponse = new String(responseBuffer, 0, responseBytes);
                System.out.println("Server response: " + serverResponse);
                if (serverResponse.equals("login succeed")) {
                    ArrayList<String> onlinePlayers = new ArrayList<>();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));
                    FXMLLoader loaderrrrr = new FXMLLoader(getClass().getResource("/tictactoe/layerOnlineUser.fxml"));
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
                    Parent onlineGamePage = loaderr.load();
                    Parent layeronline= loaderrrrr.load();
                    OnlinemodeController onlineGameController = loaderr.getController();
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
                        Platform.runLater(() -> {
                            onlineUserController.updateOnlinePlayersList(enteredUsername, onlinePlayers);
                        });
                        StringTokenizer tokenizer = new StringTokenizer(onlineUser);
                        String command = tokenizer.nextToken();
                        if (command.equals("MOVEXTO")) {
                            String playerName = tokenizer.nextToken();
                            String buttonClicked = tokenizer.nextToken();
                            String symbol = tokenizer.nextToken();
                            Platform.runLater(() -> {
                           
                              onlineGameController.setPlayerSymbol(onlineGameController.getButtonById(buttonClicked));
                            });
                            System.out.println("closeeeeeeeeeeeeeeeeeeeeeeeeeXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        }
                        if (command.equals("MOVEOTO")) {
                            String playerName = tokenizer.nextToken();
                            String buttonClicked = tokenizer.nextToken();
                            String symbol = tokenizer.nextToken();
                            Platform.runLater(() -> {
                                onlineGameController.setPlayerSymbol(onlineGameController.getButtonById(buttonClicked) );
                            });
                            System.out.println("closeeeeeeeeeeeeeeeeeeeeeeeeeOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

                        }

                        if (command.equals("userfound")) {

                            Platform.runLater(() -> {
                                Alert inviteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                inviteAlert.setTitle("Invitation");
                                inviteAlert.setHeaderText("You've received an invitation!");
                                inviteAlert.setContentText("Do you want to accept?");
                                ButtonType acceptButton = new ButtonType("Accept");
                                ButtonType rejectButton = new ButtonType("Reject");
                                inviteAlert.getButtonTypes().setAll(acceptButton, rejectButton);
                                inviteAlert.showAndWait().ifPresent(response -> {
                                    if (response == acceptButton) {
                                        System.out.println("Accepted");
                                        try {
                                            send_invitation();
                                            String acceptMessage = "accept " + enteredUsername + " " + enteredPassword;
                                            outputStream.write(acceptMessage.getBytes());
                                            outputStream.flush();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    } else if (response == rejectButton) {
                                        System.out.println("Rejected");
                                    }
                                });

                            });

                        }
                        if (command.equals("UserAccpeted")) {
                            Platform.runLater(() -> onlineGameController.setPlayer1Name(enteredUsername));
                            Platform.runLater(() -> onlineGameController.setPlayer2Name());
                            System.out.println("thhhhhhhhhhhhhhhhis gaaaaaaaame online");

                            Platform.runLater(() -> {
                                onlineUserController.closeONlineUserStage();
                                Stage stage = new Stage();
                                Scene scene = new Scene(onlineGamePage);
                                stage.setScene(scene);
                                stage.setTitle("ONLINEGame for login user" + enteredUsername);
                                stage.show();
                            });

                        } else {

                            Platform.runLater(() -> {
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(onlinePlayersPage);
                               // Scene scene = new Scene(layeronline);
                                stage.setScene(scene);
                                stage.setTitle("TicTacToe");
                                stage.show();

                            });

                        }

                    }

                    System.out.println("Login successful!");
                } else {
                    System.out.println("noooooooot");
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("User not found. Please check your credentials.");
                        alert.showAndWait();
                    });
                }
            } catch (IOException ex) {
                printStackTrace(ex);
            }

        }
        ).start();

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

    private void send_invitation() {
        new Thread(() -> {
            try {
                server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                outputStream = server.getOutputStream();
                inputStream = server.getInputStream();
                String msg = "accept" + " " + feild.getText() + " " + feild1.getText() + " " + "123456";
                System.out.println(msg);
                outputStream.write(msg.getBytes());
                byte[] responseBuffer = new byte[1024];
                int responseBytes = inputStream.read(responseBuffer);
                String serverResponse = new String(responseBuffer, 0, responseBytes);
                System.out.println("Server response: " + serverResponse);

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }).start();
    }

    public void setSelctedName(String slckted_name) {
        System.out.println("slckted_name" + slckted_name);
        this.slckted_name = slckted_name;
    }

    public void setFromName(String from_name) {
        System.out.println("from_name NAME" + from_name);
        this.from_name = from_name;
    }

    public void setboll(int boll) {

        this.boll = boll;
    }
}

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
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import onlineUserScrren.OnlineUserController;
//import tictactoeserver.ClientConnection;

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));

                    // Load the OnlineUserController and get its controller instance
                    Parent onlinePlayersPage = loader.load();
                    OnlineUserController onlineUserController = loader.getController();

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
                        Platform.runLater(() -> {
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(onlinePlayersPage);
                            stage.setScene(scene);
                            stage.setTitle("TicTacToe");
                            stage.show();
                        });

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

}

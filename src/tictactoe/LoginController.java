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
import tictactoe.helpcontroller;
import Database.DataAccessLayer;
import dto.DTO;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import onlineUserScrren.OnlineUserController;
import tictactoeserver.ClientConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

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
    DataInputStream ear;
    PrintStream mouth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void setaction(ActionEvent event) {

        AnchorPane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("login.fxml"));
            apane.getChildren().setAll(pane);

            System.out.println("cliiiiiicked");

        } catch (IOException ex) {
            Logger.getLogger(helpcontroller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("cliiiiiicked");

        }

        System.out.println("cliiiiiicked");

    }

    @FXML
    private void setaction1(ActionEvent event) {
        AnchorPane pane;
        String enteredUsername = feild.getText();
        String enteredPassword = feild1.getText();

        try {
            server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
            ear = new DataInputStream(server.getInputStream());
            GetIp();

            System.out.println("feild: " + enteredUsername);
            System.out.println("feild1: " + enteredPassword);
            System.out.println("apane: " + apane);
            if (DataAccessLayer.isValidUser(enteredUsername, enteredPassword)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));
                Parent onlinePlayersPage = loader.load();
                OnlineUserController onlinePlayersController = loader.getController();
                ArrayList<DTO> onlinePlayers = DataAccessLayer.getOnlineUsers(); // Call your method to get online players
                onlinePlayersController.updateOnlinePlayersList(onlinePlayers);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(onlinePlayersPage);
                stage.setScene(scene);
                stage.setTitle("TicTacToe");
                stage.show();
//                pane = FXMLLoader.load(getClass().getResource("/onlineUserScrren/OnlineUser.fxml"));
//                apane.getChildren().setAll(pane);
                System.out.println("login  process!");

                System.out.println("Login successful!");

            } else {
                // If not valid, show an error message or perform other actions
                System.out.println("Invalid credentials!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("cliiiiiicked");

    }

    public void GetIp() {
        try {
            mouth = new PrintStream(server.getOutputStream());
            String clientIP = server.getInetAddress().getHostAddress();

            // Send the IP address to the server
            mouth.println("IP:" + clientIP);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

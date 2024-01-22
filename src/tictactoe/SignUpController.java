/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.LoginController;

/**
 *
 * @author HP
 */
public class SignUpController implements Initializable {

    @FXML
    private AnchorPane apane;
    @FXML
    private Rectangle Rectangle;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label password1;
    @FXML
    private Text Text;
    @FXML
    private ImageView backarrow;
    @FXML
    private Button buttonregistration1;
    @FXML
    private TextField feildusername;
    @FXML
    private TextField feildpass;
    @FXML
    private TextField feildconfirm;
    Socket s;
    Socket server;
    DataInputStream ear;
    PrintStream mouth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backarrow.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) backarrow.getScene().getWindow();
                Parent myNewScene = FXMLLoader.load(getClass().getResource("/tictactoe/StartScreen.fxml"));
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("My New Scene");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void setactionRegistration(ActionEvent event) {
        AnchorPane pane;
        String enteredUsername = feildusername.getText();
        String enteredPassword = feildpass.getText();
        String ConfirmPassword = feildconfirm.getText();
        try {
            server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());
            InputStream inputStream = server.getInputStream();
            OutputStream outputStream = server.getOutputStream();
            String msg = "signup" + " " + enteredUsername + " " + enteredPassword;
            System.out.println(msg);
            mouth.println(msg);
            outputStream.write(msg.getBytes());
            System.out.println("feild: " + enteredUsername);
            System.out.println("feild1: " + enteredPassword);
            System.out.println("feild2: " + ConfirmPassword);
            System.out.println("apane: " + apane);
            if (!enteredPassword.equals(ConfirmPassword)) {
                System.out.println("Passwords do not match!");
                return;
            }
            byte[] responseBuffer = new byte[1024];
            int responseBytes = inputStream.read(responseBuffer);
            String serverResponse = new String(responseBuffer, 0, responseBytes);
            System.out.println("Server response: " + serverResponse);
            if (serverResponse.equals("signup succeed")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/login.fxml"));
                Parent onlinePlayersPage = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(onlinePlayersPage);
                stage.setScene(scene);
                stage.setTitle("TicTacToe");
                stage.show();
                System.out.println("Siiiiign up  process!");
                System.out.println("Siiiiign up  successful!");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("cliiiiiicked");

    }
}


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
import tictactoe.LoginController;

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
    String UserInvitatonFrom;
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
    String MOVEBy;
    String Sybmol_user;
    String firstPart;
    String button;

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
            List<String> tokenizedPlayers = onlinePlayers.stream()
                    .flatMap(player -> Pattern.compile(",").splitAsStream(player))
                    .map(String::trim) // Remove leading and trailing whitespaces
                    .filter(part -> !part.isEmpty()) // Filter out empty parts
                    .filter(part -> !part.equals(loginName)) // Filter out the entered login name
                    .collect(Collectors.toList());
            for (String player : onlinePlayers) {
                StringTokenizer tokenizer = new StringTokenizer(player);
                if (tokenizer.hasMoreTokens()) {
                    firstPart = tokenizer.nextToken();
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
                        System.out.println("symbol part: " + symbol);
                    }
                    if (firstPart.equals("MOVETO")) {
                        MOVEBy = firstPart;
                        String user = tokenizer.nextToken();
                        button = tokenizer.nextToken();
                        Sybmol_user = tokenizer.nextToken();
                        // WaitingAccpeted_user = tokenizer.nextToken();

                        System.out.println("MOVETO part: " + " " + MOVEBy);
                        System.out.println("Sybmol_user part: " + " " + Sybmol_user);
                    }
                    System.out.println("First part: " + firstPart);
                } else {
                    System.out.println("Invalid input");
                }
            }
            onlinePlayersListView.getItems().setAll(tokenizedPlayers);
        });
        System.out.println("onlineUserScrren.OnlineUserController.updateOnlinePlayersList()" + "onlinePlayers");
        onlinePlayersListView.setOnMouseClicked(event -> {
            String selectedItem = onlinePlayersListView.getSelectionModel().getSelectedItem();
            new Thread(() -> {
                try {
                    server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                    OutputStream outputStream = server.getOutputStream();
                    InputStream inputStream = server.getInputStream();
                    String inviteMessage = "invite" + " " + selectedItem + " " + "By" + " " + UserInvitatonFrom;
                    System.out.println(inviteMessage);
                    outputStream.write(inviteMessage.getBytes());
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/online/onlinemode.fxml"));
                    Parent onlineGamePage = loaderr.load();
                    OnlinemodeController onlineGameController = loaderr.getController();
                    FXMLLoader loaderrr = new FXMLLoader(getClass().getResource("/tictactoe/login.fxml"));
                    Parent onlinelogPage = loaderrr.load();
                    LoginController onlinelogController = loaderrr.getController();
                    onlinelogController.setSelctedName(selectedItem);
                    onlinelogController.setFromName(UserInvitatonFrom);
                    byte[] responseBuffer = new byte[1024];
                    int responseBytes = inputStream.read(responseBuffer);
                    String serverResponse = new String(responseBuffer, 0, responseBytes);
                    System.out.println("Server response: " + serverResponse);
                    StringTokenizer tokenizer = new StringTokenizer(serverResponse);
                    String command = tokenizer.nextToken();
                    String username = tokenizer.nextToken();
                    String additionalInfo = tokenizer.nextToken();
                    if (command.equals("userfound")) {
                        System.out.println("USERFOUND");
                    }
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
            if (selectedItem != null) {
                System.out.println("Selected item: " + selectedItem);
            }
        });
    }

    public void setloginName(String login_name) {
        System.out.println("LOGIN NAME" + login_name);
        this.UserInvitatonFrom = login_name;
        username.setText(login_name);
    }

    public void closeONlineUserStage() {
        Stage gameStage = (Stage) apane.getScene().getWindow();
        gameStage.close();
    }

    @FXML
    private void OnClickLogout(ActionEvent event) throws IOException {
        server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
        OutputStream outputStream = server.getOutputStream();
        InputStream inputStream = server.getInputStream();
        String msg = "LOGOUT" + " " + UserInvitatonFrom + " " + "1234" + " " + "2345";
        System.out.println(msg);
        outputStream.write(msg.getBytes());
        System.out.println("feild: " + UserInvitatonFrom);
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


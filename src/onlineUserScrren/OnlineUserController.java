
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javax.swing.text.html.ListView;

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
        // List<String> onlinePlayersList = new ArrayList<>(onlinePlayers);
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
        System.out.println("onlineUserScrren.OnlineUserController.updateOnlinePlayersList()" + "onlinePlayers");
        onlinePlayersListView.setOnMouseClicked(event -> {
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
                        if (username.equals(UserInvitatonTO)) {

                            Platform.runLater(() -> {
                                Alert inviteAlert = new Alert(Alert.AlertType.INFORMATION);
                                inviteAlert.setTitle("Invitation");
                                inviteAlert.setHeaderText("You've received an invitation!");
                                inviteAlert.setContentText("Do you want to accept?");
                                inviteAlert.showAndWait();

                            });
                        }
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

    private void inviteFirstClick(ActionEvent event) {
        new Thread(() -> {
            try {
                server = new Socket(InetAddress.getLocalHost().getHostAddress(), 5005);
                OutputStream outputStream = server.getOutputStream();
                InputStream inputStream = server.getInputStream();

                String inviteMessage = "invite" + " " + text0.getText() + " " + "1234";
                System.out.println(inviteMessage);
                outputStream.write(inviteMessage.getBytes());

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
                    if (username.equals(UserInvitatonTO)) {

                        Platform.runLater(() -> {
                            Alert inviteAlert = new Alert(Alert.AlertType.INFORMATION);
                            inviteAlert.setTitle("Invitation");
                            inviteAlert.setHeaderText("You've received an invitation!");
                            inviteAlert.setContentText("Do you want to accept?");
                            inviteAlert.showAndWait();

                        });
                    }
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(OnlineUserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OnlineUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public void setloginName(String login_name) {
        System.out.println("LOGIN NAME" + login_name);
        this.UserInvitatonTO = login_name;
    }

}


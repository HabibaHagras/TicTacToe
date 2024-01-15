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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
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
    // ArrayList<String> onlinePlayers;

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
                .flatMap(player -> Pattern.compile("online").splitAsStream(player))
                .map(String::trim) // Remove leading and trailing whitespaces
                .filter(part -> !part.isEmpty()) // Filter out empty parts
                .collect(Collectors.toList());
        System.out.println(onlinePlayers.getClass());
        onlinePlayersListView.getItems().setAll(tokenizedPlayers);
        System.out.println("onlineUserScrren.OnlineUserController.updateOnlinePlayersList()" + "onlinePlayers");
        
    }
}

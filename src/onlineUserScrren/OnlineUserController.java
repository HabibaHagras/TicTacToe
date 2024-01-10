/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineUserScrren;

import dto.DTO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    private javafx.scene.control.ListView<DTO> onlinePlayersListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML

    public void updateOnlinePlayersList(ArrayList<DTO> onlinePlayers) {
       System.out.println(onlinePlayers.getClass());
        onlinePlayersListView.getItems().setAll(onlinePlayers);
        
    }

}
